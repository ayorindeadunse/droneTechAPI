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

@Table(name = "eventlog")
public class EventLog {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;
    @Column(name = "serialNumber")
    private String serialNumber;
    @Column(name = "droneState")
    private DroneState droneState;
    @Column(name = "batteryLevel")
    private int batteryLevel;
    @Column(name = "dateCreated")
    private Date dateCreated;
    @Column(name = "dateModified")
    private Date dateModified;


    public EventLog(String serialNumber, DroneState droneState, int batteryLevel, Date dateCreated,Date dateModified) {
        this.serialNumber = serialNumber;
        this.droneState = droneState;
        this.batteryLevel = batteryLevel;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }
}

