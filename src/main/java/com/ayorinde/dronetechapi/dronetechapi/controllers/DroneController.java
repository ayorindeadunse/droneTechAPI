package com.ayorinde.dronetechapi.dronetechapi.controllers;

import com.ayorinde.dronetechapi.dronetechapi.exceptions.DroneException;
import com.ayorinde.dronetechapi.dronetechapi.models.Drone;
import com.ayorinde.dronetechapi.dronetechapi.models.DroneState;
import com.ayorinde.dronetechapi.dronetechapi.models.LoadDrone;
import com.ayorinde.dronetechapi.dronetechapi.requests.DroneRegistrationRequest;
import com.ayorinde.dronetechapi.dronetechapi.requests.LoadDroneRequest;
import com.ayorinde.dronetechapi.dronetechapi.responses.ApiResponse;
import com.ayorinde.dronetechapi.dronetechapi.services.DroneServiceImpl;
import com.ayorinde.dronetechapi.dronetechapi.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DroneController {
    private final DroneServiceImpl droneService;

    public DroneController(DroneServiceImpl droneService) {
        this.droneService = droneService;
    }

    @PostMapping("/api/droneTech/registerdrone")
    public ResponseEntity Register(@RequestBody DroneRegistrationRequest droneRegistrationRequest) {
        //Check drone weight
        if (droneRegistrationRequest.getDroneWeight() > Constants.DRONE_WEIGHT) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Maximum Drone Weight is " + Constants.DRONE_WEIGHT, droneRegistrationRequest));
        }
        Drone drone = droneService.registerDrone(droneRegistrationRequest);
        return ResponseEntity.ok(new ApiResponse(true, "Drone registered successfully!", drone));
    }
    @PostMapping("/api/droneTech/loaddrone")
    public ResponseEntity LoadDrone(@RequestBody LoadDroneRequest loadDroneRequest) throws DroneException {
        List<LoadDrone> l;
        l = droneService.loadDrone(loadDroneRequest);
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getDroneState() == DroneState.IDLE) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Battery Level is too low for this operation", l));
            }
            return ResponseEntity.ok(new ApiResponse(true, "Drone Loaded Successfully!", l));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "An Error occurred on the server", l));

    }


}
