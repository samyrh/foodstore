package food.ma.foodstore.service.managers;

import food.ma.foodstore.dao.entities.*;

import java.util.List;

public interface WishlistManager {

    void addItemToWishList(Long customerId,Long menuItemId);
    void removeItemFromWishlist(Long wishlistItemId);
    Wishlist getWishlistById(Long wishlistId);
    void clearWishlist(Long wishlistId);

    // Method to get all wishlist items for a given customer
     List<WishlistItem> getWishlistItemsForCustomer(Long customerId);
     Wishlist findOrCreateWishListByCustomerId(Long customerId);
     WishlistItem createWishListItem(Wishlist wishlist, MenuItem menuItem);

}
