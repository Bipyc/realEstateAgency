package by.bsuir.realEstateAgency.core.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Realtor extends Employee {

    @OneToMany
    private List<Immobility> immobilities;

    @OneToMany
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
