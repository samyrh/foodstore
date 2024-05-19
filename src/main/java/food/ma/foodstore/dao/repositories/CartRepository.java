package food.ma.foodstore.dao.repositories;

import food.ma.foodstore.dao.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByCustomerCustomerId(Long customerId);
}