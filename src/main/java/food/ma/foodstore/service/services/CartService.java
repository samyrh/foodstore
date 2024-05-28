package food.ma.foodstore.service.services;

import food.ma.foodstore.dao.entities.Cart;
import food.ma.foodstore.dao.entities.CartItem;
import food.ma.foodstore.dao.entities.Customer;
import food.ma.foodstore.dao.entities.MenuItem;
import food.ma.foodstore.dao.repositories.CartItemRepository;
import food.ma.foodstore.dao.repositories.CartRepository;
import food.ma.foodstore.dao.repositories.CustomerRepository;
import food.ma.foodstore.dao.repositories.MenuItemRepository;
import food.ma.foodstore.service.managers.CartManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CartService implements CartManager {
    private final CartRepository cartRepository;
    private final MenuItemRepository menuItemRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    private  CustomerRepository customerRepository;

    @Autowired
    public CartService(CartRepository cartRepository, MenuItemRepository menuItemRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.menuItemRepository = menuItemRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void addItemToCart(Long customerId, Long menuItemId, Integer quantity) {
        // Find or create the cart for the customer by their ID
        Cart cart = findOrCreateCartByCustomerId(customerId);

        // Fetch the menu item; assume the menu item exists
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        // Create and save the cart item
        CartItem cartItem = createCartItem(cart, menuItem, quantity);

        // Add the new cart item to the cart's list of items
        cart.getCartItems().add(cartItem);

        // Save the new cart item to the database
        cartItemRepository.save(cartItem);
    }

    @Override
    public void removeItemFromCart(Long cartItemId) {

        // Retrieve the cart item by its ID
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found"));

        // Retrieve the cart associated with the cart item
        Cart cart = cartItem.getCart();

        // Remove the cart item from the cart
        cart.getCartItems().remove(cartItem);

        // Save the updated cart
        cartRepository.save(cart);
    }
    @Override
    public Cart getCartById(Long cartId) {

        return cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));
    }

    @Override
    public void clearCart(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        // Clear all items from the cart
        cart.getCartItems().clear();

        // Save the updated cart
        cartRepository.save(cart);
    }

    public List<CartItem> getCartItemsForCustomer(Long customerId) {
        // Retrieve the cart for the given customer
        Cart cart = cartRepository.findByCustomerCustomerId(customerId);

        if (cart != null) {
            // If the cart exists, return its cart items
            return cart.getCartItems();
        } else {
            // If the cart does not exist (empty cart), return an empty list
            return List.of();
        }
    }

    @Override
    public Cart findOrCreateCartByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

        Cart cart = cartRepository.findByCustomerCustomerId(customerId);
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            cart = cartRepository.save(cart); // Save the new cart to the database
        }
        return cart;
    }

    @Override
    public CartItem createCartItem(Cart cart, MenuItem menuItem, Integer quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setMenuItem(menuItem);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(menuItem.getPrice() * quantity); // Calculate the total price
        return cartItem;
    }

    @Override
    public void updateCartItemQuantity(Long cartItemId, Integer quantity) {
        // Get the cart item from the repository or service based on cartItemId
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Update the quantity of the cart item
        cartItem.setQuantity(quantity);

        // Save the updated cart item back to the repository or service
        cartItemRepository.save(cartItem);
    }

    @Override
    public double calculateTotal(List<CartItem> cartItems) {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public int countTotalCartItems(Long customerId) {
        List<CartItem> cartItems = cartRepository.findAllByCustomerCustomerId(customerId);
        int totalCount = 0;
        for (CartItem cartItem : cartItems) {
            totalCount += cartItem.getQuantity();
        }
        return totalCount;
    }
}
