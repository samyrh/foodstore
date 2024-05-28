package food.ma.foodstore.service.services;

import food.ma.foodstore.dao.entities.*;
import food.ma.foodstore.dao.repositories.*;
import food.ma.foodstore.service.managers.WishlistManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService implements WishlistManager {

    @Autowired
    private CustomerRepository customerRepository;
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
    public Wishlist findOrCreateWishListByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

        Wishlist wishlist = wishlistRepository.findByCustomerCustomerId(customerId);
        if (wishlist == null) {
            wishlist = new Wishlist();
            wishlist.setCustomer(customer);
            wishlist = wishlistRepository.save(wishlist); // Save the new wishlist to the database
        }
        return wishlist;
    }

    @Override
    public WishlistItem createWishListItem(Wishlist wishlist, MenuItem menuItem) {
        WishlistItem cartItem = new WishlistItem();
        cartItem.setWishlist(wishlist);
        cartItem.setMenuItem(menuItem);
        return cartItem;
    }

    @Override
    public void addItemToWishList(Long customerId,Long menuItemId) {
        // Find or create the cart for the customer by their ID
        Wishlist wishlist = findOrCreateWishListByCustomerId(customerId);

        // Fetch the menu item; assume the menu item exists
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        // Create and save the cart item
        WishlistItem wishlistItem = createWishListItem(wishlist, menuItem);

        // Add the new cart item to the cart's list of items
        wishlist.getWishlistItems().add(wishlistItem);

        // Save the new cart item to the database
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
