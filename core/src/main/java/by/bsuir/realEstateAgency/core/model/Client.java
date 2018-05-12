package by.bsuir.realEstateAgency.core.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Client extends User {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Immobility> immobilities;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Inspection> inspections;

    public List<Immobility> getImmobilities() {
        return immobilities;
    }

    public void setImmobilities(List<Immobility> immobilities) {
        this.immobilities = immobilities;
    }

    public List<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(List<Inspection> inspections) {
        this.inspections = inspections;
    }
}
