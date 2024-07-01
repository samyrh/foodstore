package food.ma.foodstore.web.controllers;


import food.ma.foodstore.dao.entities.CartItem;
import food.ma.foodstore.dao.entities.Customer;
import food.ma.foodstore.dao.repositories.CustomerRepository;
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
public class CheckoutController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartService cartService;

    @GetMapping("/checkout")
    public String showLoginForm(Model model) {


        // Get the currently authenticated user's authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //Side Cart
        // Assuming the authentication principal is a UserDetails object
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();

            // Retrieve the customer ID by username (you might need to modify this based on your CustomerRepository method)
            Long customerId = customerRepository.findCustomerByUsername(username).getCustomerId();


            Customer customer = customerRepository.getReferenceById(customerId);
            model.addAttribute("customercheckout", customer);


            // Get the cart items for the customer
            List<CartItem> cartItems = cartService.getCartItemsForCustomer(customerId);

            double total = cartService.calculateTotal(cartItems);
            // Add the cart items to the model to be used in the view
            model.addAttribute("cartitems", cartItems);

            model.addAttribute("totalcart", total);
        } else {
            return "redirect:/login";
        }
        //End side cart
        return "checkout";
    }


}
