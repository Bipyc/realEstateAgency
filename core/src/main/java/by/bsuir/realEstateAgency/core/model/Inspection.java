package by.bsuir.realEstateAgency.core.model;

import javax.persistence.*;
import java.util.Date;

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

    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column
    @Temporal(TemporalType.TIME)
    private Date time;

    @Lob
    @Column
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
