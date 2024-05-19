package food.ma.foodstore.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MenuItem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double price;


    private String url;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

}