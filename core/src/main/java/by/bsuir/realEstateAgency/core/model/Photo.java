package by.bsuir.realEstateAgency.core.model;

import javax.persistence.*;

@Entity
@Table(name = "Photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String path;

    @ManyToOne(cascade = CascadeType.ALL)
    private Immobility immobility;
}
