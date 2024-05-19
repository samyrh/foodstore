package food.ma.foodstore.service.services;

import food.ma.foodstore.dao.entities.Cart;
import food.ma.foodstore.dao.entities.CartItem;
import food.ma.foodstore.dao.entities.MenuItem;
import food.ma.foodstore.dao.repositories.CartItemRepository;
import food.ma.foodstore.dao.repositories.CartRepository;
import food.ma.foodstore.dao.repositories.MenuItemRepository;
import food.ma.foodstore.service.managers.CartItemManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService implements CartItemManager {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository, MenuItemRepository menuItemRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public void addCartItem(Long cartId, Long itemId, Integer quantity) {

        // Retrieve the cart by its ID
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        // Retrieve the menu item by its ID
        MenuItem menuItem = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Menu item not found"));

        // Create a new cart item
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setMenuItem(menuItem);
        cartItem.setQuantity(quantity);

        // Save the new cart item
        cartItemRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(Long cartItemId) {

        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found"));
    }
}

