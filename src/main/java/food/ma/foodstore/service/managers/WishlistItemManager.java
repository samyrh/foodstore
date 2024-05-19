package food.ma.foodstore.service.managers;

import food.ma.foodstore.dao.entities.WishlistItem;

public interface WishlistItemManager {

    void addItemToWishlist(Long wishlistId, Long itemId);
    void removeItemFromWishlist(Long wishlistItemId);
    WishlistItem getWishlistItemById(Long wishlistItemId);
}
