package food.ma.foodstore.web.controllers;


import food.ma.foodstore.dao.entities.MenuItem;
import food.ma.foodstore.dao.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping("/fast")
    public String showMenuFast(Model model) {
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


    @GetMapping("/restaurant")
    public String showMenuRestaurant(Model model) {

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
}
