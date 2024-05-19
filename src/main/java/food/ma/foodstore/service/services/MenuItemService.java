package food.ma.foodstore.service.services;

import food.ma.foodstore.dao.entities.Category;
import food.ma.foodstore.dao.entities.MenuItem;
import food.ma.foodstore.dao.repositories.MenuItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItem createMenuItem(MenuItem menuItem) {

        return menuItemRepository.save(menuItem);
    }

    public MenuItem getMenuItemById(Long menuItemId) {

        return menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new EntityNotFoundException("Menu item not found"));
    }

    public MenuItem updateMenuItem(MenuItem menuItem) {

        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(Long menuItemId) {

        menuItemRepository.deleteById(menuItemId);
    }

    public List<MenuItem> getAllMenuItems() {

        return menuItemRepository.findAll();
    }

    public List<MenuItem> getMenuItemsByCategory(Long id) {

        return menuItemRepository.findByCategoryCategoryId(id);
    }

}

