package by.bsuir.realEstateAgency.web.bean.application;

import by.bsuir.realEstateAgency.core.model.ApplicationStatus;
import by.bsuir.realEstateAgency.core.model.Immobility;
import by.bsuir.realEstateAgency.core.model.Realtor;
import by.bsuir.realEstateAgency.core.model.TypeApplication;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class ApplicationDto {
    private Long id;

    private Date date;

    @NotEmpty
    private String realtorName;

    @NotNull
    @Min(1L)
    private Long immobilityId;

    @NotNull
    @Min(0L)
    private Long typeId;

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

    public String getRealtorName() {
        return realtorName;
    }

    public void setRealtorName(String realtorName) {
        this.realtorName = realtorName;
    }

    public Long getImmobilityId() {
        return immobilityId;
    }

    public void setImmobilityId(Long immobilityId) {
        this.immobilityId = immobilityId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
