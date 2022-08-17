package com.ayorinde.dronetechapi.dronetechapi.services;

import com.ayorinde.dronetechapi.dronetechapi.exceptions.DroneException;
import com.ayorinde.dronetechapi.dronetechapi.models.*;
import com.ayorinde.dronetechapi.dronetechapi.repositories.*;
import com.ayorinde.dronetechapi.dronetechapi.requests.DroneRegistrationRequest;
import com.ayorinde.dronetechapi.dronetechapi.requests.GetDroneBatteryLevelRequest;
import com.ayorinde.dronetechapi.dronetechapi.requests.GetMedicationRequest;
import com.ayorinde.dronetechapi.dronetechapi.requests.LoadDroneRequest;
import com.ayorinde.dronetechapi.dronetechapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        DroneRegister droneRegister;
        String serialNumber = droneRegistrationRequest.setSerialNumber(); //create unique serial number for drone registration.
        try
        {
            Drone d = checkDroneExists(serialNumber);
            if(d != null)
            {
                System.out.println("Drone with Serial Number "+serialNumber +" already exists.");
            }
            drone = new Drone();
            drone.setSerialNumber(serialNumber);
            drone.setDroneModel(droneRegistrationRequest.getDroneModel());
            drone.setDroneWeight(droneRegistrationRequest.getDroneWeight());
            drone.setBatteryCapacity(Constants.BATTERY_CAPACITY);
            drone.setDroneState(DroneState.IDLE);

            drone1 = droneRepository.save(drone);
            if(drone1.getSerialNumber() != null)
            {
                // save  in DroneRegister table
                droneRegister = new DroneRegister();
                droneRegister.setSerialNumber(drone1.getSerialNumber());
                droneRegister.setCreatedDate(new Date());
                droneRegister.setModifiedDate(new Date());

                DroneRegister dr1 = droneRegisterRepository.save(droneRegister);
                if(dr1.getId() > 0)
                {
                    System.out.println("Drone Successfully Registered");
                    //Log state in event log
                    String auditEntry = "Drone Registered";
                    addEventLog(drone1,auditEntry);
                }


            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    void addEventLog(Drone dr,String auditEntry)
    {
       EventLog eventLog = new EventLog();
       eventLog.setSerialNumber(dr.getSerialNumber());
       eventLog.setDroneState(dr.getDroneState());
       eventLog.setBatteryLevel(dr.getBatteryCapacity());
       eventLog.setDateCreated(new Date());
       eventLog.setDateModified(new Date());

       EventLog eventLog1 = eventLogRepository.save(eventLog);
       System.out.println("New Audit log created "+ auditEntry+ ". Log entry: "+eventLog1.getId());
    }
    @Override
    public DroneState getSelectedDroneState(String serialNumber) {
        DroneState droneState = DroneState.IDLE;
        try
        {
            droneState = eventLogRepository.getCurrentDroneState(serialNumber);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return droneState;
    }

    @Override
    public int getBatteryLevel(String serialNumber) {

        int batteryLevel = 0;
        try
        {
            batteryLevel = eventLogRepository.getDroneBatteryLevel(serialNumber);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return batteryLevel;
    }

    @Override
    public List<Integer> getDroneBatteryLevel(GetDroneBatteryLevelRequest getDroneBatteryLevelRequest) {
        List<Integer> batteryLevel = new ArrayList<>();
        try
        {
            batteryLevel = eventLogRepository.getDroneBatteryLevell(getDroneBatteryLevelRequest.getSerialNumber());
            System.out.println(batteryLevel);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return batteryLevel;
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
