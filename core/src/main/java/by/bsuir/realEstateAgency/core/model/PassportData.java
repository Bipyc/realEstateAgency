package by.bsuir.realEstateAgency.core.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Passports")
public class PassportData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String number;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateOfIssue;

    @Column(name = "indentNuber")
    private String indentificationNumber;

    @Column
    private String authority;

    @Column
    private String patronymic;

    @OneToOne
    private User user;
}
