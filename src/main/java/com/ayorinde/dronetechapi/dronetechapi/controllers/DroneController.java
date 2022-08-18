package com.ayorinde.dronetechapi.dronetechapi.controllers;

import com.ayorinde.dronetechapi.dronetechapi.services.DroneServiceImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DroneController {
    private final DroneServiceImpl droneService;

    public DroneController(DroneServiceImpl droneService) {
        this.droneService = droneService;
    }

}
