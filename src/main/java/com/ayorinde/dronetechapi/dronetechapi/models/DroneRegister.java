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

@Table(name = "droneregister")
public class DroneRegister {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id; // primary key
    @Column(name = "serialNumber",unique = true)
    private String serialNumber; //make this a unique field
    @Column(name = "createdDate")
    private Date createdDate;
    @Column(name = "modifiedDate")
    private Date modifiedDate;

}

