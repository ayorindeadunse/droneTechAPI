package com.ayorinde.dronetechapi.dronetechapi.services;

import com.ayorinde.dronetechapi.dronetechapi.models.Drone;
import com.ayorinde.dronetechapi.dronetechapi.requests.DroneRegistrationRequest;

public interface DroneService {
    Drone registerDrone(DroneRegistrationRequest drone); //get drone serialNumber
}
