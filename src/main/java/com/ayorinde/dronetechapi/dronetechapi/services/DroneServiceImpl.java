package com.ayorinde.dronetechapi.dronetechapi.services;

import com.ayorinde.dronetechapi.dronetechapi.exceptions.DroneException;
import com.ayorinde.dronetechapi.dronetechapi.models.Drone;
import com.ayorinde.dronetechapi.dronetechapi.models.DroneState;
import com.ayorinde.dronetechapi.dronetechapi.models.EventLog;
import com.ayorinde.dronetechapi.dronetechapi.models.LoadDrone;
import com.ayorinde.dronetechapi.dronetechapi.repositories.*;
import com.ayorinde.dronetechapi.dronetechapi.requests.DroneRegistrationRequest;
import com.ayorinde.dronetechapi.dronetechapi.requests.GetDroneBatteryLevelRequest;
import com.ayorinde.dronetechapi.dronetechapi.requests.GetMedicationRequest;
import com.ayorinde.dronetechapi.dronetechapi.requests.LoadDroneRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneServiceImpl implements DroneService,MedicationService{

    private final DroneRepository droneRepository;
    private final DroneRegisterRepository droneRegisterRepository;
    private final EventLogRepository eventLogRepository;
    private final MedicationRepository medicationRepository;
    private final LoadDroneRepository loadDroneRepository;
    private final MedicationRegisterRepository medicationRegisterRepository;

    @Autowired
    public DroneServiceImpl(DroneRepository droneRepository, DroneRegisterRepository droneRegisterRepository, EventLogRepository eventLogRepository, MedicationRepository medicationRepository, LoadDroneRepository loadDroneRepository, MedicationRegisterRepository medicationRegisterRepository) {
        this.droneRepository = droneRepository;
        this.droneRegisterRepository = droneRegisterRepository;
        this.eventLogRepository = eventLogRepository;
        this.medicationRepository = medicationRepository;
        this.loadDroneRepository = loadDroneRepository;
        this.medicationRegisterRepository = medicationRegisterRepository;
    }

    @Override
    public Drone registerDrone(DroneRegistrationRequest droneRegistrationRequest) {
        // check if the drone exists
        Drone drone, drone1;
        String serialNumber = droneRegistrationRequest.setSerialNumber(); //create unique serial number for drone registration.
        try
        {
            Drone d = checkDroneExists(serialNumber);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

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
        Drone dd = null;
        try
        {
            dd = droneRepository.checkIfDroneExists(serialNumber);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return dd;
    }

    @Override
    public List<EventLog> getLogHistory() {
        return null;
    }

    @Override
    public int getMedicationWeight(String code) {
        return 0;
    }
}
