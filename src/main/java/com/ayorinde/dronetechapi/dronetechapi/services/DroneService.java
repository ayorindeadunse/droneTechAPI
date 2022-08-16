package com.ayorinde.dronetechapi.dronetechapi.services;

import com.ayorinde.dronetechapi.dronetechapi.models.Drone;
import com.ayorinde.dronetechapi.dronetechapi.models.DroneState;
import com.ayorinde.dronetechapi.dronetechapi.requests.DroneRegistrationRequest;
import com.ayorinde.dronetechapi.dronetechapi.requests.GetDroneBatteryLevelRequest;

import java.util.List;

public interface DroneService {
    Drone registerDrone(DroneRegistrationRequest drone); //get drone serialNumber
    DroneState getSelectedDroneState(String serialNumber);//get the drone state. Use a switchcase method to return the droneState
    int getBatteryLevel(String serialNumber); //get the battery level for a particular drone from event log.
    List<Integer> getDroneBatteryLevel(GetDroneBatteryLevelRequest getDroneBatteryLevelRequest);
    List<String> getAvailableDrones(); //get the list of available drones from the event log. The serial numbers will be added to the ArrayList object.
}
