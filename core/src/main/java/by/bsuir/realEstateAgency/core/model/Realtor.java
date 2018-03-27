package by.bsuir.realEstateAgency.core.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Realtor extends User {

    public Realtor() {
        super();
        setTypeUser(TypeUser.REALTOR);
    }

    @Column
    @Temporal(TemporalType.DATE)
    private Date employmentDate;

    @Column
    private BigDecimal salary;

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
