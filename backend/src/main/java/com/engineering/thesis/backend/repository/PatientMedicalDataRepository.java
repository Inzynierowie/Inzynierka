package com.engineering.thesis.backend.repository;

import com.engineering.thesis.backend.model.PatientMedicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientMedicalDataRepository extends JpaRepository<PatientMedicalData, Long> {
}