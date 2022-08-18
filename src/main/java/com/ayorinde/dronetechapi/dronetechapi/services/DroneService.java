package com.ayorinde.dronetechapi.dronetechapi.services;

import com.ayorinde.dronetechapi.dronetechapi.exceptions.DroneException;
import com.ayorinde.dronetechapi.dronetechapi.exceptions.MedicationException;
import com.ayorinde.dronetechapi.dronetechapi.models.*;
import com.ayorinde.dronetechapi.dronetechapi.requests.*;

import java.util.List;

public interface DroneService {
    Drone registerDrone(DroneRegistrationRequest drone); //get drone serialNumber
    DroneState getSelectedDroneState(String serialNumber);//get the drone state. Use a switchcase method to return the droneState
    int getBatteryLevel(String serialNumber); //get the battery level for a particular drone from event log.
    List<Integer> getDroneBatteryLevel(GetDroneBatteryLevelRequest getDroneBatteryLevelRequest);
    List<String> getAvailableDrones(); //get the list of available drones from the event log. The serial numbers will be added to the ArrayList object.
    List<LoadDrone> loadDrone(LoadDroneRequest loadDrone) throws DroneException; //return the drone state after loading the drone with medication.
    List<String> getLoadedMedication(GetMedicationRequest getMedicationRequest); //get a list of loaded medication for a given drone
    int recalculateBatteryLevel(int currentBatteryLevel, int medicineCount); /*assumed logic for drone battery level calculation */
    Drone checkDroneExists(String serialNumber);
    Medication registerMedication(MedicationRegistrationRequest medication) throws MedicationException; //register a medication item in inventory and return the details; /** extra method **/
    List<EventLog> getLogHistory();
}
