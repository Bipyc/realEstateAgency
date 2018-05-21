package by.bsuir.realEstateAgency.web.bean;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class InspectionDto {
    private Long id;

    @NotNull
    private String clientName;

    @NotNull
    private String realtorName;

    @NotNull
    @Min(1L)
    private Long immobilityId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @DateTimeFormat(pattern = "HH:mm")
    private Date time;

    @Size(max = 2048)
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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
