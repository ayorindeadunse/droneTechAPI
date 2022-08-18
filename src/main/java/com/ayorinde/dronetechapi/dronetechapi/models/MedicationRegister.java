package com.ayorinde.dronetechapi.dronetechapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "medicationregister")
public class MedicationRegister {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id; // primary key
    @Column(name = "medicationCode")
    private String medicationCode; //make this a unique field
    @Column(name = "createdDate")
    private Date createdDate;
    @Column(name = "modifiedDate")
    private Date modifiedDate;

    public MedicationRegister(String medicationCode, Date createdDate, Date modifiedDate) {
        this.medicationCode = medicationCode;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}

