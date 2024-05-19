package food.ma.foodstore.dao.repositories;

import food.ma.foodstore.dao.entities.Category;
import food.ma.foodstore.dao.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByCategoryCategoryId(Long id);
    List<MenuItem> findByCategoryName(String category);

}
