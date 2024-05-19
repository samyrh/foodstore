package food.ma.foodstore.service.services;

import food.ma.foodstore.dao.entities.Customer;
import food.ma.foodstore.dao.entities.Reservation;
import food.ma.foodstore.dao.repositories.ReservationRepository;
import food.ma.foodstore.service.managers.ReservationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationService implements ReservationManager {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {

        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservationById(Long reservationId) {

        return reservationRepository.findById(reservationId).orElse(null);
    }

    @Override
    public void updateReservation(Reservation reservation) {

        // Check if the reservation exists
        if (reservationRepository.existsById(reservation.getReservationId())) {
            // Update the reservation
            reservationRepository.save(reservation);
        } else {
            // Handle the case where the reservation does not exist
            throw new RuntimeException("Reservation with ID " + reservation.getReservationId() + " not found");
        }
    }

    @Override
    public void deleteReservation(Long reservationId) {

        reservationRepository.deleteById(reservationId);
    }

    @Override
    public List<Reservation> getReservationsByCustomer(Customer customer) {

        // Implement logic to retrieve reservations by customer ID
        return reservationRepository.findByCustomer(customer);
    }
}
