package food.ma.foodstore.service.managers;

import food.ma.foodstore.dao.entities.Cart;
import food.ma.foodstore.dao.entities.CartItem;
import java.util.List;

public interface CartManager {

    void addItemToCart(Long cartId, Long itemId, Integer quantity);
    void removeItemFromCart(Long cartItemId);
    Cart getCartById(Long cartId);
    void clearCart(Long cartId);

    List<CartItem> getCartItemsForCustomer(Long customerId);


}

