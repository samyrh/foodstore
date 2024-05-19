package food.ma.foodstore.service.services;

import food.ma.foodstore.dao.entities.MenuItem;
import food.ma.foodstore.dao.entities.Wishlist;
import food.ma.foodstore.dao.entities.WishlistItem;
import food.ma.foodstore.dao.repositories.MenuItemRepository;
import food.ma.foodstore.dao.repositories.WishlistItemRepository;
import food.ma.foodstore.dao.repositories.WishlistRepository;
import food.ma.foodstore.service.managers.WishlistManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService implements WishlistManager {

    private final WishlistRepository wishlistRepository;
    private final WishlistItemRepository wishlistItemRepository;
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository, WishlistItemRepository wishlistItemRepository,MenuItemRepository menuItemRepository) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistItemRepository = wishlistItemRepository;
        this.menuItemRepository = menuItemRepository;
    }
    @Override
    public List<WishlistItem> getWishlistItemsForCustomer(Long customerId) {
        // Retrieve the wishlist for the given customer
        Wishlist wishlist = wishlistRepository.findByCustomerCustomerId(customerId);

        if (wishlist != null) {
            // If the wishlist exists, return its wishlist items
            return wishlist.getWishlistItems();
        } else {
            // If the wishlist does not exist (empty wishlist), return an empty list
            return List.of();
        }
    }
    @Override
    public void addItemToWishlist(Long wishlistId, Long itemId) {

        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist not found"));

        MenuItem menuItem = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Menu item not found"));

        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setWishlist(wishlist);
        wishlistItem.setMenuItem(menuItem);

        wishlistItemRepository.save(wishlistItem);
    }

    @Override
    public void removeItemFromWishlist(Long wishlistItemId) {

        WishlistItem wishlistItem = wishlistItemRepository.findById(wishlistItemId)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist item not found"));

        wishlistItemRepository.delete(wishlistItem);
    }

    @Override
    public Wishlist getWishlistById(Long wishlistId) {

        return wishlistRepository.findById(wishlistId)
                .orElse(null);
    }

    @Override
    public void clearWishlist(Long wishlistId) {

        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist not found"));

        List<WishlistItem> wishlistItems = wishlist.getWishlistItems();
        wishlistItems.clear();
        wishlistRepository.save(wishlist);
    }
}
