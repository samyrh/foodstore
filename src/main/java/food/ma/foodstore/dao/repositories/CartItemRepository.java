package food.ma.foodstore.dao.repositories;

import food.ma.foodstore.dao.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {



}
