package food.ma.foodstore.dao.repositories;

import food.ma.foodstore.dao.entities.Customer;
import food.ma.foodstore.dao.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomer(Customer customer);

}

