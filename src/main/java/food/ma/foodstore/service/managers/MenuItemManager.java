package food.ma.foodstore.service.managers;

import food.ma.foodstore.dao.entities.MenuItem;
import java.util.List;

public interface MenuItemManager {
    MenuItem createMenuItem(MenuItem menuItem);
    MenuItem getMenuItemById(Long menuItemId);
    void updateMenuItem(MenuItem menuItem);
    void deleteMenuItem(Long menuItemId);
    List<MenuItem> getAllMenuItems();
    List<MenuItem> getMenuItemsByCategory(Long id);
}
