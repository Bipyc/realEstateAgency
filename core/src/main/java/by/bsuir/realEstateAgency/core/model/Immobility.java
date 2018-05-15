package by.bsuir.realEstateAgency.core.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Immobility")
public class Immobility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Client owner;

    @Lob
    @Column
    private String description;

    @Column
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.ALL)
    private City city;

    @Column
    private int numberOfRooms;

    @Column
    private double square;

    @Column
    private String address;

    @Enumerated
    @Column(columnDefinition = "smallint")
    private TypeImmobility type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "immobility")
    private List<Photo> photos;
}
