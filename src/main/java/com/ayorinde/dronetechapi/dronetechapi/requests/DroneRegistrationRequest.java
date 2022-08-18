package com.ayorinde.dronetechapi.dronetechapi.requests;

import com.ayorinde.dronetechapi.dronetechapi.models.DroneModel;

import java.util.UUID;

public class DroneRegistrationRequest {
    private int droneWeight;
    private DroneModel droneModel;
    public String createSerialNumber()
    {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }

    public String setSerialNumber() {

        String serialNumber = createSerialNumber();
        return serialNumber;
    }

    public int getDroneWeight() {
        return droneWeight;
    }
    public void setDroneWeight(int droneWeight) {
        this.droneWeight = droneWeight;
    }
    public DroneModel getDroneModel() {
        return droneModel;
    }

    public void setDroneModel(DroneModel droneModel) {
        this.droneModel = droneModel;
    }
}
