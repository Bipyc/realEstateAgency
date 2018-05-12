package by.bsuir.realEstateAgency.core.model;

import javax.persistence.*;

@Entity
@Table(name = "Inspections")
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    private Realtor realtor;

    @ManyToOne(cascade = CascadeType.ALL)
    private Immobility immobility;

    @Lob
    @Column
    private String comment;
}
