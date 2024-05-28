package food.ma.foodstore.web.controllers;

import food.ma.foodstore.dao.entities.CartItem;
import food.ma.foodstore.dao.entities.WishlistItem;
import food.ma.foodstore.dao.repositories.CustomerRepository;
import food.ma.foodstore.dao.repositories.WishlistRepository;
import food.ma.foodstore.service.managers.CartManager;
import food.ma.foodstore.service.managers.WishlistManager;
import food.ma.foodstore.service.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WishListController {


    @Autowired
    private WishlistManager wishlistManager;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartService cartService;

    @GetMapping("/addtowishlist/{itemId}")
    public String addToWishList(@PathVariable Long itemId) {
        // Get the currently authenticated user's authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Assuming the authentication principal is a UserDetails object
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();

            // Retrieve the customer ID by username (you might need to modify this based on your CustomerRepository method)
            Long customerId = customerRepository.findCustomerByUsername(username).getCustomerId();

            // Add the item to the cart using the retrieved customer ID
            wishlistManager.addItemToWishList(customerId, itemId); // Assuming quantity is 1 for now
        } else {
            // Handle case where authentication principal is not a UserDetails object
            throw new RuntimeException("User details not found in authentication principal");
        }

        // Redirect to the shop page or any other desired page
        return "redirect:/wishlist";
    }

    @GetMapping("/wishlist/removeitem/{itemId}")
    public String removeItemShopPage(@PathVariable Long itemId) {
        // Call the CartService method to remove the item from the cart
        cartService.removeItemFromCart(itemId);

        // Redirect to the cart page or any other desired page after removal
        return "redirect:/wishlist"; // Redirect to the cart page
    }


    @GetMapping("/wishlist")
    public String viewCart(Model model) {
        // Get the currently authenticated user's authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Assuming the authentication principal is a UserDetails object
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();

            // Retrieve the customer ID by username (you might need to modify this based on your CustomerRepository method)
            Long customerId = customerRepository.findCustomerByUsername(username).getCustomerId();

            // Get the cart items for the customer
            List<WishlistItem> wishlistItems = wishlistManager.getWishlistItemsForCustomer(customerId);

            // Add the cart items to the model to be used in the view
            model.addAttribute("wishlistItems", wishlistItems);

            // Total of cart Items
            List<CartItem> cartItems = cartService.getCartItemsForCustomer(customerId);
            int totalCartItems = cartItems.size();
            model.addAttribute("totalCartItems", totalCartItems);
            // Load all Menu Items in Side Cart
            model.addAttribute("cartItems", cartItems);


            // the amount of side cart TOTAL
            double total = cartService.calculateTotal(cartItems);
            model.addAttribute("totalcart", total);
        } else {
            return "redirect:/login";
        }

        return "wishlist";
    }


    @GetMapping("/wishlist/remove/{itemId}")
    public String removeWishListItem(@PathVariable Long itemId) {
        // Call the CartService method to remove the item from the cart
        wishlistManager.removeItemFromWishlist(itemId);

        // Redirect to the cart page or any other desired page after removal
        return "redirect:/wishlist"; // Redirect to the cart page
    }

    @GetMapping("/wishlist/addtocart/{itemId}")
    public String addToCart(@PathVariable("itemId") Long itemId, RedirectAttributes redirectAttributes) {
        // Get the currently authenticated user's authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Assuming the authentication principal is a UserDetails object
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();

            // Retrieve the customer ID by username (you might need to modify this based on your CustomerRepository method)
            Long customerId = customerRepository.findCustomerByUsername(username).getCustomerId();

            // Add the item to the cart using the retrieved customer ID
            cartService.addItemToCart(customerId, itemId, 1); // Assuming quantity is 1 for now


            return "redirect:/cart";
        } else {
            return "redirect:/login";
        }
    }

}
