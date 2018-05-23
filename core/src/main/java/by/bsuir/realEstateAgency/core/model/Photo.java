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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Immobility getImmobility() {
        return immobility;
    }

    public void setImmobility(Immobility immobility) {
        this.immobility = immobility;
    }
}
