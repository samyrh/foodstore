package food.ma.foodstore.dao.repositories;

import food.ma.foodstore.dao.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // You can define custom query methods here if needed
}
