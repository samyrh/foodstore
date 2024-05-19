package food.ma.foodstore.service.managers;

import food.ma.foodstore.dao.entities.CartItem;

public interface CartItemManager {
    void addCartItem(Long cartId, Long itemId, Integer quantity);
    void removeCartItem(Long cartItemId);
    CartItem getCartItemById(Long cartItemId);
}
