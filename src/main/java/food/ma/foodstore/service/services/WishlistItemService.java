package food.ma.foodstore.service.services;

import food.ma.foodstore.dao.entities.MenuItem;
import food.ma.foodstore.dao.entities.Wishlist;
import food.ma.foodstore.dao.entities.WishlistItem;
import food.ma.foodstore.dao.repositories.WishlistItemRepository;
import food.ma.foodstore.service.managers.WishlistItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistItemService implements WishlistItemManager {

    private final WishlistItemRepository wishlistItemRepository;

    @Autowired
    public WishlistItemService(WishlistItemRepository wishlistItemRepository) {
        this.wishlistItemRepository = wishlistItemRepository;
    }

    @Override
    public void addItemToWishlist(Long wishlistId, Long itemId) {

        WishlistItem wishlistItem = new WishlistItem();

        // Create instances of Wishlist and MenuItem
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistId(wishlistId); // Set wishlistId if needed

        MenuItem menuItem = new MenuItem();
        menuItem.setItemId(itemId); // Set itemId if needed

        // Set associations
        wishlistItem.setWishlist(wishlist);
        wishlistItem.setMenuItem(menuItem);

        // Save the wishlist item
        wishlistItemRepository.save(wishlistItem);
    }


    @Override
    public void removeItemFromWishlist(Long wishlistItemId) {
        // Implement logic to remove an item from the wishlist
        wishlistItemRepository.deleteById(wishlistItemId);
    }

    @Override
    public WishlistItem getWishlistItemById(Long wishlistItemId) {
        // Implement logic to retrieve a wishlist item by ID
        return wishlistItemRepository.findById(wishlistItemId).orElse(null);
    }
}
