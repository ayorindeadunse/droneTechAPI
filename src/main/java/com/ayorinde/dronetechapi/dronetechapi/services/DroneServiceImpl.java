package com.ayorinde.dronetechapi.dronetechapi.services;

import com.ayorinde.dronetechapi.dronetechapi.exceptions.DroneException;
import com.ayorinde.dronetechapi.dronetechapi.models.Drone;
import com.ayorinde.dronetechapi.dronetechapi.models.DroneState;
import com.ayorinde.dronetechapi.dronetechapi.models.EventLog;
import com.ayorinde.dronetechapi.dronetechapi.models.LoadDrone;
import com.ayorinde.dronetechapi.dronetechapi.requests.DroneRegistrationRequest;
import com.ayorinde.dronetechapi.dronetechapi.requests.GetDroneBatteryLevelRequest;
import com.ayorinde.dronetechapi.dronetechapi.requests.GetMedicationRequest;
import com.ayorinde.dronetechapi.dronetechapi.requests.LoadDroneRequest;

import java.util.List;

public class DroneServiceImpl implements DroneService{
    @Override
    public Drone registerDrone(DroneRegistrationRequest drone) {
        return null;
    }

    @Override
    public DroneState getSelectedDroneState(String serialNumber) {
        return null;
    }

    @Override
    public int getBatteryLevel(String serialNumber) {
        return 0;
    }

    @Override
    public List<Integer> getDroneBatteryLevel(GetDroneBatteryLevelRequest getDroneBatteryLevelRequest) {
        return null;
    }

    @Override
    public List<String> getAvailableDrones() {
        return null;
    }

    @Override
    public List<LoadDrone> loadDrone(LoadDroneRequest loadDrone) throws DroneException {
        return null;
    }

    @Override
    public List<String> getLoadedMedication(GetMedicationRequest getMedicationRequest) {
        return null;
    }

    @Override
    public int recalculateBatteryLevel(int currentBatteryLevel, int medicineCount) {
        return 0;
    }

    @Override
    public Drone checkDroneExists(String serialNumber) {
        return null;
    }

    @Override
    public List<EventLog> getLogHistory() {
        return null;
    }
}
