package com.ayorinde.dronetechapi.dronetechapi.repositories;

import com.ayorinde.dronetechapi.dronetechapi.models.DroneState;
import com.ayorinde.dronetechapi.dronetechapi.models.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventLogRepository extends JpaRepository<EventLog,Long> {

    @Query(value = "SELECT SERIAL_NUMBER FROM EVENTLOG WHERE DRONE_STATE = 0 ORDER BY DATE_CREATED DESC LIMIT 1", nativeQuery=true)
    List<String> getAllAvailableDrones();

    @Query(value = "SELECT BATTERY_LEVEL from EVENTLOG WHERE SERIAL_NUMBER = ?1 ORDER BY DATE_CREATED DESC LIMIT 1",nativeQuery = true)
    int getDroneBatteryLevel(String serialNumber);

    @Query(value = "SELECT BATTERY_LEVEL from EVENTLOG WHERE SERIAL_NUMBER = ?1 ORDER BY DATE_CREATED DESC LIMIT 1",nativeQuery = true)
    List<Integer> getDroneBatteryLevell(String serialNumber);

    @Query(value = "SELECT DRONE_STATE FROM EVENTLOG WHERE SERIAL_NUMBER = ?1 ORDER BY DATE_CREATED DESC LIMIT 1", nativeQuery=true)
    DroneState getCurrentDroneState(String serialNumber);

}
