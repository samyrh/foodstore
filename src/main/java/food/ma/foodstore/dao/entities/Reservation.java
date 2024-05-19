package food.ma.foodstore.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    private LocalDateTime reservationDateTime;
    private Integer numberOfGuests;
    private String status;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

}