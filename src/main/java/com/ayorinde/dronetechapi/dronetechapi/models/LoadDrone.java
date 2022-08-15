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
@Table(name = "loadeddrones")
public class LoadDrone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "serialNumber")
    private String serialNumber;
    @Column(name = "medicineCode")
    private String medicineCode;
    @Column(name = "droneState")
    private DroneState droneState;
    @Column(name = "dateCreated")
    private Date dateCreated;
    @Column(name = "dateModified")
    private Date dateModified;
}

