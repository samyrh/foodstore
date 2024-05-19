package food.ma.foodstore.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {


    @GetMapping("/checkout")
    public String showLoginForm() {

        return "checkout";
    }

}
