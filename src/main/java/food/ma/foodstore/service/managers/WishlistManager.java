package food.ma.foodstore.service.managers;

import food.ma.foodstore.dao.entities.Wishlist;
import food.ma.foodstore.dao.entities.WishlistItem;
import java.util.List;

public interface WishlistManager {

    void addItemToWishlist(Long wishlistId, Long itemId);
    void removeItemFromWishlist(Long wishlistItemId);
    Wishlist getWishlistById(Long wishlistId);
    void clearWishlist(Long wishlistId);

    // Method to get all wishlist items for a given customer
     List<WishlistItem> getWishlistItemsForCustomer(Long customerId);

}
