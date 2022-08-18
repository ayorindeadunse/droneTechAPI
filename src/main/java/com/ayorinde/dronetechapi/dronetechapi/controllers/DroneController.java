package com.ayorinde.dronetechapi.dronetechapi.controllers;

import com.ayorinde.dronetechapi.dronetechapi.models.Drone;
import com.ayorinde.dronetechapi.dronetechapi.requests.DroneRegistrationRequest;
import com.ayorinde.dronetechapi.dronetechapi.responses.ApiResponse;
import com.ayorinde.dronetechapi.dronetechapi.services.DroneServiceImpl;
import com.ayorinde.dronetechapi.dronetechapi.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        //return ResponseEntity.ok(d);
        return ResponseEntity.ok(new ApiResponse(true, "Drone registered successfully!", drone));
    }
}
