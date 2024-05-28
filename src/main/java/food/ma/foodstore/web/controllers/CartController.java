package food.ma.foodstore.web.controllers;

import food.ma.foodstore.dao.entities.CartItem;
import food.ma.foodstore.dao.entities.Customer;
import food.ma.foodstore.dao.entities.MenuItem;
import food.ma.foodstore.dao.repositories.CustomerRepository;
import food.ma.foodstore.service.managers.CartManager;
import food.ma.foodstore.service.services.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartManager cartManager;
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/addtocart/{itemId}")
    public String addToCart(@PathVariable Long itemId) {
        // Get the currently authenticated user's authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Assuming the authentication principal is a UserDetails object
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();

            // Retrieve the customer ID by username (you might need to modify this based on your CustomerRepository method)
            Long customerId = customerRepository.findCustomerByUsername(username).getCustomerId();

            // Add the item to the cart using the retrieved customer ID
            cartService.addItemToCart(customerId, itemId, 1); // Assuming quantity is 1 for now
        } else {
            // Handle case where authentication principal is not a UserDetails object
            throw new RuntimeException("User details not found in authentication principal");
        }

        // Redirect to the shop page or any other desired page
        return "redirect:/cart";
    }





    @GetMapping("/cart")
    public String viewCart(Model model) {
        // Get the currently authenticated user's authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Assuming the authentication principal is a UserDetails object
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();

            // Retrieve the customer ID by username (you might need to modify this based on your CustomerRepository method)
            Long customerId = customerRepository.findCustomerByUsername(username).getCustomerId();

            // Get the cart items for the customer
            List<CartItem> cartItems = cartService.getCartItemsForCustomer(customerId);
            List<CartItem> carttotal = cartService.getCartItemsForCustomer(customerId);
            double total = cartService.calculateTotal(cartItems);
            // Add the cart items to the model to be used in the view
            model.addAttribute("cartItems", cartItems);

            model.addAttribute("total", total);

            // Total of cart Items
            int totalCartItems = cartItems.size();
            model.addAttribute("totalCartItems", totalCartItems);
        } else {
            return "redirect:/login";
        }

        return "cart";
    }


    @GetMapping("/removeitem/{itemId}")
    public String removeCartItem(@PathVariable Long itemId) {
        // Call the CartService method to remove the item from the cart
        cartService.removeItemFromCart(itemId);

        // Redirect to the cart page or any other desired page after removal
        return "redirect:/cart"; // Redirect to the cart page
    }

    @GetMapping("/cart/removeitem/{itemId}")
    public String removeItemBlogPage(@PathVariable Long itemId) {
        // Call the CartService method to remove the item from the cart
        cartService.removeItemFromCart(itemId);

        // Redirect to the cart page or any other desired page after removal
        return "redirect:/cart"; // Redirect to the cart page
    }





    @PostMapping("/updatecart")
    public String updateCartItemQuantity(@ModelAttribute("cartItemId") Long cartItemId, @ModelAttribute("quantity") int quantity, Model model) {
        // Update the cart item quantity using the CartService
        cartService.updateCartItemQuantity(cartItemId, quantity);

        // Redirect back to the cart page or any other desired page
        return "redirect:/cart";
    }

}
