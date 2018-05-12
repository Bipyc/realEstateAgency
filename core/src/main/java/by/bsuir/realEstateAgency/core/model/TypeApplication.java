package by.bsuir.realEstateAgency.core.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TypeApplications")
public class TypeApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal commission;
}
