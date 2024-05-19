package food.ma.foodstore.service.managers;

import food.ma.foodstore.dao.entities.Category;

import java.util.List;

public interface CategoryManager {
    Category createCategory(Category category);
    Category getCategoryById(Long categoryId);
    void updateCategory(Category category);
    void deleteCategory(Long categoryId);
    List<Category> getAllCategories();
}
