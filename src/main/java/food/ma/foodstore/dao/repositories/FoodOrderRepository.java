package food.ma.foodstore.dao.repositories;

import food.ma.foodstore.dao.entities.Customer;
import food.ma.foodstore.dao.entities.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    List<FoodOrder> findByCustomer(Customer customer);
}
