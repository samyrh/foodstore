package food.ma.foodstore.dao.repositories;

import food.ma.foodstore.dao.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByCustomerCustomerId(Long customerId);
}
