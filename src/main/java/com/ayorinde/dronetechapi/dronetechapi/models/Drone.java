package com.ayorinde.dronetechapi.dronetechapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

@Table(name = "drones")
public class Drone {
    // Declare drone properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; //auto increment. Identity column.
    @Column(name = "serialNumber",unique = true)
    private String serialNumber;
    @Column(name = "droneModel")
    private DroneModel droneModel;
    @Column(name = "droneWeight")
    private int droneWeight;
    @Column(name = "batteryCapacity")
    private int batteryCapacity;
    @Column(name = "droneState")
    private DroneState droneState;

}

