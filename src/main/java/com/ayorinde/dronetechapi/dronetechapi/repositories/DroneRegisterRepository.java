package com.ayorinde.dronetechapi.dronetechapi.repositories;

import com.ayorinde.dronetechapi.dronetechapi.models.DroneRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRegisterRepository extends JpaRepository<DroneRegister,Long> {
}
