package food.ma.foodstore.web.controllers;
import food.ma.foodstore.dao.entities.CartItem;
import food.ma.foodstore.dao.entities.MenuItem;
import food.ma.foodstore.dao.repositories.CustomerRepository;
import food.ma.foodstore.dao.repositories.MenuItemRepository;
import food.ma.foodstore.service.managers.CartManager;
import food.ma.foodstore.service.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ShopController {

    private final MenuItemRepository menuItemService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartManager cartManager;
    @Autowired
    private CartService cartService;
    @Autowired
    public ShopController(MenuItemRepository menuItemService) {

        this.menuItemService = menuItemService;
    }

    @GetMapping("/shop")
    public String shopPage(Model model) {


        // Get the currently authenticated user's authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //Side Cart
        // Assuming the authentication principal is a UserDetails object
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();

            // Retrieve the customer ID by username (you might need to modify this based on your CustomerRepository method)
            Long customerId = customerRepository.findCustomerByUsername(username).getCustomerId();

            // Get the cart items for the customer
            List<CartItem> cartItems = cartService.getCartItemsForCustomer(customerId);

            double total = cartService.calculateTotal(cartItems);
            // Add the cart items to the model to be used in the view
            model.addAttribute("cartitems", cartItems);

            model.addAttribute("totalcart", total);

            // Total of cart Items
            int totalCartItems = cartItems.size();
            model.addAttribute("totalCartItems", totalCartItems);
        } else {
            return "redirect:/login";
        }
        //End side cart

        // Fetch all menu items
        List<MenuItem> menuItems = menuItemService.findAll();

        // Add the menu items to the model
        model.addAttribute("menuItems", menuItems);

        // Return the view name
        return "shop";
    }

    @GetMapping("/shopdetails")
    public String showContact() {

        return "shopdetails";
    }

    @GetMapping("/shop/removeitem/{itemId}")
    public String removeItemShopPage(@PathVariable Long itemId) {
        // Call the CartService method to remove the item from the cart
        cartService.removeItemFromCart(itemId);

        // Redirect to the cart page or any other desired page after removal
        return "redirect:/shop"; // Redirect to the cart page
    }

}

