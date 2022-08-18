package com.ayorinde.dronetechapi.dronetechapi.controllers;

import com.ayorinde.dronetechapi.dronetechapi.exceptions.DroneException;
import com.ayorinde.dronetechapi.dronetechapi.exceptions.MedicationException;
import com.ayorinde.dronetechapi.dronetechapi.models.*;
import com.ayorinde.dronetechapi.dronetechapi.requests.*;
import com.ayorinde.dronetechapi.dronetechapi.responses.ApiResponse;
import com.ayorinde.dronetechapi.dronetechapi.services.DroneServiceImpl;
import com.ayorinde.dronetechapi.dronetechapi.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DroneController {
    private DroneServiceImpl droneService;

    @Autowired
    public DroneController(DroneServiceImpl droneService) {
        this.droneService = droneService;
    }

    @PostMapping("/api/dronetech/registerdrone")
    public ResponseEntity Register(@RequestBody DroneRegistrationRequest droneRegistrationRequest) {
        //Check drone weight
        if (droneRegistrationRequest.getDroneWeight() > Constants.DRONE_WEIGHT) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Maximum Drone Weight is " + Constants.DRONE_WEIGHT, droneRegistrationRequest));
        }
        Drone drone = droneService.registerDrone(droneRegistrationRequest);
        return ResponseEntity.ok(new ApiResponse(true, "Drone registered successfully!", drone));
    }
    @PostMapping("/api/dronetech/loaddrone")
    public ResponseEntity LoadDrone(@RequestBody LoadDroneRequest loadDroneRequest) throws DroneException {
        List<LoadDrone> l;
        l = droneService.loadDrone(loadDroneRequest);
        if(l.size() != 0)
            return ResponseEntity.ok(new ApiResponse(true, "Drone Loaded Successfully!", l));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "An Error occurred on the server", l));
    }

    @PostMapping("/api/dronetech/registermedication")
    public ResponseEntity registerMedication(@RequestBody MedicationRegistrationRequest medication) throws MedicationException {
        Medication m = droneService.registerMedication(medication);
        if(m != null) {
            return ResponseEntity.ok(new ApiResponse(true, "Medicine Successfully registered.", m));
        }
        return ResponseEntity.ok(new ApiResponse(false, "An error occurred", m));
    }

    @PostMapping("/api/dronetech/getloadedmedication")
    public ResponseEntity getLoadedMedication(@RequestBody GetMedicationRequest getMedicationRequest) {
        List<String> getMeds = droneService.getLoadedMedication(getMedicationRequest);
        if (getMeds.size() != 0) {
            return ResponseEntity.ok(new ApiResponse(true, "Medication Successfully  loaded", getMeds));
        }
        return ResponseEntity.ok(new ApiResponse(false, "No data retrieved", getMeds));
    }
    @GetMapping("/api/dronetech/getavailabledrones")
    public ResponseEntity getAvailableDrones() {
        List<String> getDrones = droneService.getAvailableDrones();
        if (getDrones.size() != 0) {
            return ResponseEntity.ok(new ApiResponse(true, "Available Drones Successfully retrieved", getDrones));
        }
        return ResponseEntity.ok(new ApiResponse(false, "No data retrieved", getDrones));
    }

    @PostMapping("/api/dronetech/getdronebatterylevel")
    public ResponseEntity getDroneBatteryLevel(@RequestBody GetDroneBatteryLevelRequest getDroneBatteryLevelRequest) {
        List<Integer> droneBatteryLevelResponse = droneService.getDroneBatteryLevel(getDroneBatteryLevelRequest);
        if (droneBatteryLevelResponse.size() != 0) {
            return ResponseEntity.ok(new ApiResponse(true, "Current Drone Battery Level Returned", droneBatteryLevelResponse));
        }
        return ResponseEntity.ok(new ApiResponse(false, "No data retrieved", droneBatteryLevelResponse));
    }

    //get log history
    @GetMapping("/api/dronetech/geteventlog")
    public ResponseEntity getLogs()
    {
        List<EventLog> ev = droneService.getLogHistory();

            return ResponseEntity.ok(ev);
    }
}
