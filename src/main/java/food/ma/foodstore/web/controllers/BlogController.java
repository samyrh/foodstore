package food.ma.foodstore.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {


    @GetMapping("/blog")
    public String showBlog() {

        return "blog";
    }
}
