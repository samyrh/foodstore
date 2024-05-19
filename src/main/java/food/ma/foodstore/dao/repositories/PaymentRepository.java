package food.ma.foodstore.dao.repositories;

import food.ma.foodstore.dao.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // You can define custom query methods here if needed
}
