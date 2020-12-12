package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.PatientMedicalData;

import java.util.List;
import java.util.Optional;

public interface PatientMedicalDataService {
    PatientMedicalData create(PatientMedicalData patientMedicalData);
    PatientMedicalData update(PatientMedicalData patientMedicalData);
    void deleteById(Long id);
    List<PatientMedicalData> selectAll();
    Optional<PatientMedicalData> selectPatientMedicalDataById(Long id);
}