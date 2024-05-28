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

import java.util.Collections;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private  MenuItemRepository menuItemManager;


    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerRepository customerRepository;




    @GetMapping("/index")
    public String showIndex(Model model) {

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

        // Récupérer tous les éléments de menu avec la catégorie ayant l'ID égal à 1
        List<MenuItem> menuItems = menuItemManager.findAll();

        // Ajouter les éléments de menu à l'objet Model pour les rendre disponibles dans la vue HTML
        model.addAttribute("menuItems", menuItems);


        // Fetch all menu items from repository
        List<MenuItem> allMenuItems = menuItemManager.findAll();
        Collections.shuffle(allMenuItems);

        // Take first 8 items if available, otherwise take all items
        List<MenuItem> customMenuItems = allMenuItems.size() > 8 ? allMenuItems.subList(0, 8) : allMenuItems;

        // Add menu items to model
        model.addAttribute("customMenuItems", customMenuItems);

        // Return view name
        return "index";
    }


    @GetMapping("/index/removeitem/{itemId}")
    public String removeItem(@PathVariable Long itemId) {
        // Call the CartService method to remove the item from the cart
        cartService.removeItemFromCart(itemId);

        // Redirect to the cart page or any other desired page after removal
        return "redirect:/index"; // Redirect to the cart page
    }



    @GetMapping("/index2")
    public String showIndex2() {

        return "index2";
    }


    @GetMapping("/index3")
    public String showIndex3(Model model) {
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

        List<MenuItem> menuItems = menuItemManager.findAll();
        // Shuffle the list of menu items
        Collections.shuffle(menuItems);

        // Add the shuffled list of menu items to the model
        model.addAttribute("menuItems", menuItems);


        return "index3";
    }

    @GetMapping("/index3/removeitem/{itemId}")
    public String removeItemIndex3(@PathVariable Long itemId) {
        // Call the CartService method to remove the item from the cart
        cartService.removeItemFromCart(itemId);

        // Redirect to the cart page or any other desired page after removal
        return "redirect:/index3"; // Redirect to the cart page
    }



    @GetMapping("/index4")
    public String showIndex4(Model model) {

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

        List<MenuItem> menuItems = menuItemManager.findAll();
        model.addAttribute("menuItems", menuItems);
        return "index4";
    }



    @GetMapping("/index4/removeitem/{itemId}")
    public String removeItemindex4(@PathVariable Long itemId) {
        // Call the CartService method to remove the item from the cart
        cartService.removeItemFromCart(itemId);

        // Redirect to the cart page or any other desired page after removal
        return "redirect:/index4"; // Redirect to the cart page
    }

    @GetMapping("/index5")
    public String showIndex5(Model model) {
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
        return "index5";
    }

    @GetMapping("/index5/removeitem/{itemId}")
    public String removeItemIndex5(@PathVariable Long itemId) {

        // Call the CartService method to remove the item from the cart
        cartService.removeItemFromCart(itemId);

        // Redirect to the cart page or any other desired page after removal
        return "redirect:/index5"; // Redirect to the cart page
    }

    @GetMapping("/")
    public String showindexdefault() {

        return "index";
    }

}
