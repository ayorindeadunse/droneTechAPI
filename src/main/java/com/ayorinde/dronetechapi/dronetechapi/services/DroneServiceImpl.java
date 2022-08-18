package com.ayorinde.dronetechapi.dronetechapi.services;

import com.ayorinde.dronetechapi.dronetechapi.exceptions.DroneException;
import com.ayorinde.dronetechapi.dronetechapi.exceptions.MedicationException;
import com.ayorinde.dronetechapi.dronetechapi.models.*;
import com.ayorinde.dronetechapi.dronetechapi.repositories.*;
import com.ayorinde.dronetechapi.dronetechapi.requests.*;
import com.ayorinde.dronetechapi.dronetechapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DroneServiceImpl implements DroneService,MedicationService{

    private  DroneRepository droneRepository;
    private  DroneRegisterRepository droneRegisterRepository;
    private  EventLogRepository eventLogRepository;
    private  MedicationRepository medicationRepository;
    private  LoadDroneRepository loadDroneRepository;
    private  MedicationRegisterRepository medicationRegisterRepository;

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
        Drone drone, drone1 = null;
        DroneRegister droneRegister = null;
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

        return drone1;
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
    public List<String> getAvailableDrones()
    {
        List<String> availableDrones = new ArrayList<>();
        try
        {
            availableDrones = eventLogRepository.getAllAvailableDrones();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return availableDrones;
    }
    @Override
    public List<LoadDrone> loadDrone(LoadDroneRequest loadDroneRequest) throws DroneException{
        List<LoadDrone> loaded = new ArrayList<>();
        int droneWeight,totalMedicationWeight = 0;
        LoadDrone loadDrone,loadDrone1,loadDrone2,loadDrone3 = null;
        DroneState checkDroneState = getSelectedDroneState(loadDroneRequest.getSerialNumber());
        if(checkDroneState == DroneState.IDLE)
        {
        // load drone
            int batteryLevel = getBatteryLevel(loadDroneRequest.getSerialNumber());
            droneWeight = droneRepository.getDroneWeight(loadDroneRequest.getSerialNumber());
            for(int i = 0; i<loadDroneRequest.getMedicineCode().size(); i++)
            {
                int medicationWeight = getMedicationWeight(loadDroneRequest.getMedicineCode().get(i));
                totalMedicationWeight += medicationWeight;
                if(batteryLevel < 25)
                {
                    LoadDrone l = new LoadDrone();
                    l.setSerialNumber(loadDroneRequest.getSerialNumber());
                    l.setMedicineCode(loadDroneRequest.getMedicineCode().get(i));
                    l.setDroneState(DroneState.IDLE);
                    l.setDateCreated(new Date());
                    l.setDateModified(new Date());

                    loaded.add(l);
                    throw new DroneException("Drone battery level is too low for this operation");
                }
                else if(totalMedicationWeight > droneWeight)
                {
                    LoadDrone l1 = new LoadDrone();
                    l1.setSerialNumber(loadDroneRequest.getSerialNumber());
                    l1.setMedicineCode(loadDroneRequest.getMedicineCode().get(i));
                    l1.setDroneState(DroneState.OVERLOADED);
                    l1.setDateCreated(new Date());
                    l1.setDateModified(new Date());

                    loaded.add(l1);
                    throw new DroneException("Drone cannot be loaded because the content is too heavy.");
                }
                else {
                    loadDrone = new LoadDrone();
                    loadDrone.setSerialNumber(loadDroneRequest.getSerialNumber());
                    loadDrone.setMedicineCode(loadDroneRequest.getMedicineCode().get(i));
                    loadDrone.setDroneState(DroneState.LOADING);
                    loadDrone.setDateCreated(new Date());
                    loadDrone.setDateModified(new Date());

                    //save drone load
                    loadDrone1 = loadDroneRepository.save(loadDrone);

                    if(loadDrone1.getId() > 0)
                    {
                        // change state to loaded
                        loadDrone2 = new LoadDrone();
                        loadDrone2.setSerialNumber(loadDroneRequest.getSerialNumber());
                        loadDrone2.setMedicineCode(loadDroneRequest.getMedicineCode().get(i));
                        loadDrone2.setDroneState(DroneState.LOADED);
                        loadDrone2.setDateCreated(new Date());
                        loadDrone2.setDateModified(new Date());

                        loadDrone3 = loadDroneRepository.save(loadDrone2);
                    }
                    //load the medication onto drone
                    loaded.add(loadDrone);
                    //Set drone state to loading
                    EventLog eLoading = new EventLog();
                    eLoading.setSerialNumber(loadDrone.getSerialNumber());
                    eLoading.setDroneState(DroneState.LOADING);
                    eLoading.setBatteryLevel(recalculateBatteryLevel(batteryLevel, i + 1));
                    eLoading.setDateCreated(new Date());
                    eLoading.setDateModified((new Date()));

                    eventLogRepository.save(eLoading);
                }
            }
            // if saved successfully, recalculate  drone battery level and save in event log.
            if (loadDrone3.getId() > 0) {
                int medicineCount = loadDroneRequest.getMedicineCode().size();
                int newBatteryLevel = recalculateBatteryLevel(batteryLevel, medicineCount);

                /*  assign a new Drone state for the battery level*/

                EventLog el = new EventLog();
                el.setSerialNumber(loadDroneRequest.getSerialNumber());
                el.setDroneState(DroneState.LOADED);
                el.setBatteryLevel(newBatteryLevel);
                el.setDateCreated(new Date());
                el.setDateModified((new Date()));

                EventLog e = eventLogRepository.save(el);
                System.out.println("New Audit record logged(Load Drone) " + e.getId());
                // set delivery, delivered and returning logs
                EventLog eDelivering = new EventLog(loadDroneRequest.getSerialNumber(), DroneState.DELIVERING, newBatteryLevel,
                        new Date(), new Date());
                eventLogRepository.save(eDelivering);

                EventLog eDelivered = new EventLog(loadDroneRequest.getSerialNumber(), DroneState.DELIVERED,
                        newBatteryLevel, new Date(), new Date());
                eventLogRepository.save(eDelivered);

                EventLog eReturning = new EventLog(loadDroneRequest.getSerialNumber(), DroneState.RETURNING,
                        newBatteryLevel, new Date(), new Date());
                eventLogRepository.save(eReturning);

                // set back to idle
                EventLog eIdle = new EventLog(loadDroneRequest.getSerialNumber(),DroneState.IDLE,newBatteryLevel,
                        new Date(),new Date());
                eventLogRepository.save(eIdle);
            }
          //  return "Drone with Serial Number: "+loadDroneRequest.getSerialNumber() +" successfully was successfully loaded. Delivery of medication successfully completed.";
        }
        else
        {
           System.out.println("Drone with Serial Number: "+loadDroneRequest.getSerialNumber() + "is currently engaged. Please try " +
                    "again later.");
          //  return "Drone with Serial Number: "+loadDroneRequest.getSerialNumber() + "is currently engaged. Please try " +
                  //  "again later.";
        }
        return loaded;
    }

    @Override
    public List<String> getLoadedMedication(GetMedicationRequest getMedicationRequest) {
        List<String> loadedMedication = new ArrayList<>();
        try
        {
            loadedMedication = loadDroneRepository.getDroneAvailableMedication(getMedicationRequest.getSerialNumber());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return loadedMedication;
    }

    @Override
    public int recalculateBatteryLevel(int currentBatteryLevel, int medicineCount)
    {
        return currentBatteryLevel - medicineCount;
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
    public Medication registerMedication(MedicationRegistrationRequest medication) throws MedicationException
    {
        Medication m,me = null;
            //check if the medication already exists.
            m = medicationRepository.checkIfMedicationExists(medication.getCode());
            if (m != null) // medication already exists
            {
                throw new MedicationException("Medication with code "+medication.getCode() +" already exists");
            }

            me = new Medication();
            me.setName(medication.getName());
            me.setMedicineWeight(medication.getMedicineWeight());
            me.setCode(medication.getCode());
            me.setMedicationImageUrl(medication.getMedicationImage());

            Medication me1 = medicationRepository.save(me);
            if(me1.getId() > 0)
            {
                //save in medication register
                MedicationRegister medicationRegister = new MedicationRegister(me.getCode(),new Date(),new Date());
                medicationRegisterRepository.save(medicationRegister);

                System.out.println("Medication with code " + medication.getCode() + "has been registered");
            }

        return me;
    }


    @Override
    public List<EventLog> getLogHistory()
    {
        List<EventLog> total = null;
        try
        {
            total = eventLogRepository.findAll();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public int getMedicationWeight(String code)
    {
        int weight = 0;
        try
        {
            weight = medicationRepository.medicationWeight(code);
            System.out.println("The medication for" + code + " is:" + weight + "g");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return weight;
    }
}
