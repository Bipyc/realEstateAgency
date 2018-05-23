package by.bsuir.realEstateAgency.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Employee extends User {

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

