package com.ayorinde.dronetechapi.dronetechapi.repositories;

import com.ayorinde.dronetechapi.dronetechapi.models.MedicationRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRegisterRepository extends JpaRepository<MedicationRegister,Long> {
}
