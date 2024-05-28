package food.ma.foodstore.service.managers;

import food.ma.foodstore.dao.entities.Cart;
import food.ma.foodstore.dao.entities.CartItem;
import food.ma.foodstore.dao.entities.Customer;
import food.ma.foodstore.dao.entities.MenuItem;

import java.util.List;

public interface CartManager {

    int countTotalCartItems(Long customerId);
    void addItemToCart(Long customerId, Long menuItemId, Integer quantity);
    void removeItemFromCart(Long cartItemId);
    Cart getCartById(Long cartId);
    void clearCart(Long cartId);

    List<CartItem> getCartItemsForCustomer(Long customerId);

    Cart findOrCreateCartByCustomerId(Long customerId);
    CartItem createCartItem(Cart cart, MenuItem menuItem, Integer quantity);

    void updateCartItemQuantity(Long cartItemId, Integer quantity);

    double calculateTotal(List<CartItem> cartItems);
}

