package food.ma.foodstore.dao.repositories;

import food.ma.foodstore.dao.entities.Cart;
import food.ma.foodstore.dao.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByCustomerCustomerId(Long customerId);
    List<CartItem> findAllByCustomerCustomerId(Long customerId);
}