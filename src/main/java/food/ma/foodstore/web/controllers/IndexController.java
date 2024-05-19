package food.ma.foodstore.web.controllers;

import food.ma.foodstore.dao.entities.MenuItem;
import food.ma.foodstore.dao.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private  MenuItemRepository menuItemManager;


    @GetMapping("/index")
    public String showIndex(Model model) {


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

    @GetMapping("/index2")
    public String showIndex2() {

        return "index2";
    }


    @GetMapping("/index3")
    public String showIndex3(Model model) {


        List<MenuItem> menuItems = menuItemManager.findAll();
        // Shuffle the list of menu items
        Collections.shuffle(menuItems);

        // Add the shuffled list of menu items to the model
        model.addAttribute("menuItems", menuItems);


        return "index3";
    }

    @GetMapping("/index4")
    public String showIndex4(Model model) {
        List<MenuItem> menuItems = menuItemManager.findAll();
        model.addAttribute("menuItems", menuItems);
        return "index4";
    }

    @GetMapping("/index5")
    public String showIndex5() {

        return "index5";
    }


}
