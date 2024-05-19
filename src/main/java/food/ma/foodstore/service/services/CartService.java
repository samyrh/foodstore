package food.ma.foodstore.service.services;

import food.ma.foodstore.dao.entities.Cart;
import food.ma.foodstore.dao.entities.CartItem;
import food.ma.foodstore.dao.entities.MenuItem;
import food.ma.foodstore.dao.repositories.CartItemRepository;
import food.ma.foodstore.dao.repositories.CartRepository;
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
    public CartService(CartRepository cartRepository, MenuItemRepository menuItemRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.menuItemRepository = menuItemRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void addItemToCart(Long cartId, Long itemId, Integer quantity) {

        // Get the cart
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        // Get the menu item
        MenuItem menuItem = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Menu item not found"));

        // Create a new cart item
        CartItem cartItem = new CartItem();
        cartItem.setMenuItem(menuItem);
        cartItem.setQuantity(quantity);

        // Add the cart item to the cart
        cart.getCartItems().add(cartItem);

        // Save the updated cart
        cartRepository.save(cart);
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
}
