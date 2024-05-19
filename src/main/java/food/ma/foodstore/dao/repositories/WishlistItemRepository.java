package food.ma.foodstore.dao.repositories;

import food.ma.foodstore.dao.entities.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    // You can define custom query methods here if needed
}

