package food.ma.foodstore.web.controllers;
import food.ma.foodstore.dao.entities.MenuItem;
import food.ma.foodstore.dao.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShopController {

    private final MenuItemRepository menuItemService;

    @Autowired
    public ShopController(MenuItemRepository menuItemService) {

        this.menuItemService = menuItemService;
    }

    @GetMapping("/shop")
    public String shopPage(Model model) {
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

}

