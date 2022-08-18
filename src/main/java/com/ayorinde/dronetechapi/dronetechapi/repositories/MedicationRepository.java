package com.ayorinde.dronetechapi.dronetechapi.repositories;

import com.ayorinde.dronetechapi.dronetechapi.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication,Long> {

    @Query(value = "SELECT MEDICINE_WEIGHT from MEDICATIONS where CODE = ?1",nativeQuery = true)
    int medicationWeight(String medicationCode);

    @Query(value = "SELECT * FROM MEDICATIONS WHERE CODE = ?1",nativeQuery = true)
    Medication checkIfMedicationExists(String code);
}
