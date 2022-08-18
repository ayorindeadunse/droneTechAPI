package com.ayorinde.dronetechapi.dronetechapi.repositories;

import com.ayorinde.dronetechapi.dronetechapi.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone,Long> {
    @Query(value ="SELECT DRONE_WEIGHT from DRONES WHERE SERIAL_NUMBER = ?1",nativeQuery = true)
    int getDroneWeight(String serialNumber);

    @Query(value = "SELECT * FROM DRONES WHERE SERIAL_NUMBER = ?1",nativeQuery = true)
    Drone checkIfDroneExists(String serialNumber);
}
