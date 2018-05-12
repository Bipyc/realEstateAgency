package by.bsuir.realEstateAgency.core.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Deals")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Application application;

    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal commission;
}
