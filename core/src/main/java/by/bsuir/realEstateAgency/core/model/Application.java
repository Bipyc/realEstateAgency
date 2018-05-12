package by.bsuir.realEstateAgency.core.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    private Realtor realtor;

    @ManyToOne(cascade = CascadeType.ALL)
    private Immobility immobility;

    @ManyToOne(cascade = CascadeType.ALL)
    private TypeApplication type;

    @Enumerated
    @Column(columnDefinition = "smallint")
    private ApplicationStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Realtor getRealtor() {
        return realtor;
    }

    public void setRealtor(Realtor realtor) {
        this.realtor = realtor;
    }

    public Immobility getImmobility() {
        return immobility;
    }

    public void setImmobility(Immobility immobility) {
        this.immobility = immobility;
    }

    public TypeApplication getType() {
        return type;
    }

    public void setType(TypeApplication type) {
        this.type = type;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
