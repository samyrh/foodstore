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
public class MenuController {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartManager cartManager;
    @Autowired
    private CartService cartService;

    @GetMapping("/fast")
    public String showMenuFast(Model model) {

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


        // Retrieve all burgers from the repository
        List<MenuItem> burgers = menuItemRepository.findByCategoryCategoryId(1L);

        // Add the list of burgers to the model
        model.addAttribute("burgers", burgers);
        // Retrieve all burgers from the repository
        List<MenuItem> pizzas = menuItemRepository.findByCategoryCategoryId(2L);

        // Add the list of burgers to the model
        model.addAttribute("pizzas", pizzas);

        List<MenuItem> pastas = menuItemRepository.findByCategoryCategoryId(3L);

        // Add the list of pasta items to the model with the key "pastas"
        model.addAttribute("pastas", pastas);

        List<MenuItem> desserts = menuItemRepository.findByCategoryCategoryId(4L);

        // Add the list of pasta items to the model with the key "pastas"
        model.addAttribute("desserts", desserts);

        return "menufast";
    }


    @GetMapping("/menufast/removeitem/{itemId}")
    public String removeItemFastPage(@PathVariable Long itemId) {
        // Call the CartService method to remove the item from the cart
        cartService.removeItemFromCart(itemId);

        // Redirect to the cart page or any other desired page after removal
        return "redirect:/fast"; // Redirect to the cart page
    }


    @GetMapping("/restaurant")
    public String showMenuRestaurant(Model model) {


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


        List<MenuItem> breakfastFood = menuItemRepository.findByCategoryCategoryId(5L);

        // Add the list of pasta items to the model with the key "pastas"
        model.addAttribute("breakfastFood", breakfastFood);

        List<MenuItem> lunchfood = menuItemRepository.findByCategoryCategoryId(6L);

        // Add the list of pasta items to the model with the key "pastas"
        model.addAttribute("lunchfood", lunchfood);

        List<MenuItem> dinnerfood = menuItemRepository.findByCategoryCategoryId(7L);

        // Add the list of pasta items to the model with the key "pastas"
        model.addAttribute("dinnerfood", dinnerfood);

        List<MenuItem> healthyfood = menuItemRepository.findByCategoryCategoryId(8L);

        // Add the list of pasta items to the model with the key "pastas"
        model.addAttribute("healthyfood", healthyfood);

        return "menurestaurant";
    }

    @GetMapping("/restaurant/removeitem/{itemId}")
    public String removeItemRestaurantPage(@PathVariable Long itemId) {
        // Call the CartService method to remove the item from the cart
        cartService.removeItemFromCart(itemId);

        // Redirect to the cart page or any other desired page after removal
        return "redirect:/restaurant"; // Redirect to the cart page
    }
}
