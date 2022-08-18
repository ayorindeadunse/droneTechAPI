package com.ayorinde.dronetechapi.dronetechapi.repositories;

import com.ayorinde.dronetechapi.dronetechapi.models.LoadDrone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadDroneRepository extends JpaRepository<LoadDrone,Long> {
    @Query(value = "SELECT MEDICINE_CODE FROM LOADEDDRONES WHERE SERIAL_NUMBER = ?1 AND DRONE_STATE = 2", nativeQuery=true)
    List<String> getDroneAvailableMedication(String serialNumber);
}
