package food.ma.foodstore.dao.repositories;

import food.ma.foodstore.dao.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // You can define custom query methods here if needed
}
