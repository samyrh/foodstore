package food.ma.foodstore.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/Login")
    public String showLoginForm() {
        return "login";
    }
}
