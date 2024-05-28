package food.ma.foodstore.web.controllers;

import food.ma.foodstore.dao.entities.CartItem;
import food.ma.foodstore.dao.entities.Reservation;
import food.ma.foodstore.dao.repositories.CustomerRepository;
import food.ma.foodstore.service.managers.ReservationManager;
import food.ma.foodstore.service.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ReservationManager reservationManager;
    @Autowired
    private CartService cartService;


    @GetMapping("/reservation")
    public String showReservation(Model model) {

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
        return "reservation";
    }

    @GetMapping("/reservation/removeitem/{itemId}")
    public String removeItemReservationPage(@PathVariable Long itemId) {
        // Call the CartService method to remove the item from the cart
        cartService.removeItemFromCart(itemId);

        // Redirect to the cart page or any other desired page after removal
        return "redirect:/reservation"; // Redirect to the cart page
    }

    @PostMapping("/reserve")
    public String reserve(@RequestParam Integer numberOfGuests,
                          @RequestParam String reservationDateTime,
                          @RequestParam String description,
                          Model model) {

        // Get the currently authenticated user's authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Assuming the authentication principal is a UserDetails object
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();

            // Retrieve the customer ID by username (you might need to modify this based on your CustomerRepository method)
            Long customerId = customerRepository.findCustomerByUsername(username).getCustomerId();

            // Convert the String date to a Date object
            Date parsedDate = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                parsedDate = dateFormat.parse(reservationDateTime);
            } catch (ParseException e) {
                // Handle the parse exception if the date format is incorrect
                e.printStackTrace();
            }

            // Create a Reservation object
            Reservation reservation = new Reservation();
            reservation.setNumberOfGuests(numberOfGuests);
            reservation.setReservationDateTime(parsedDate); // Set reservationDateTime as Date
            reservation.setDescription(description); // Set description

            // Call the reservationManager to add the reservation
            reservationManager.addReservation(customerId, reservation);

            return "redirect:/index"; // Redirect to cart page after successful reservation
        } else {
            return "redirect:/login"; // Redirect to login page if user is not authenticated
        }
    }


}
