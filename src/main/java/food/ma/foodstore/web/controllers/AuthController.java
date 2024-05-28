package food.ma.foodstore.web.controllers;

import food.ma.foodstore.dao.entities.Customer;
import food.ma.foodstore.dao.repositories.CustomerRepository;
import food.ma.foodstore.service.managers.CustomerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private CustomerManager customerManager;
    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/login")
    public String login() {

        return "auth";
    }



    @PostMapping("/register")
    public String registerCustomer(@RequestParam("username") String username,
                                   @RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   @RequestParam("confirmPassword") String confirmPassword,
                                   @RequestParam("address") String address,
                                   @RequestParam("phoneNumber") String phoneNumber,
                                   Model model) {
        // Your registration logic here
        // Create a new Customer object and save to database, etc.
        Customer newCustomer = new Customer();
        newCustomer.setUsername(username);
        newCustomer.setEmail(email);
        newCustomer.setPassword(password);
        newCustomer.setAddress(address);
        newCustomer.setPhoneNumber(phoneNumber);
        customerRepository.save(newCustomer);

        return "redirect:/login"; // Redirect to login page after successful registration
    }
}
