package food.ma.foodstore.service.managers;

import food.ma.foodstore.dao.entities.Customer;
import food.ma.foodstore.dao.entities.Reservation;

import java.util.List;

public interface ReservationManager {

    Reservation createReservation(Reservation reservation);
    Reservation getReservationById(Long reservationId);
    void updateReservation(Reservation reservation);
    void deleteReservation(Long reservationId);
    List<Reservation> getReservationsByCustomer(Customer customer);
}
