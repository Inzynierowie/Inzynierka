package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.PatientMedicalData;

import java.util.List;
import java.util.Optional;

public interface PatientMedicalDataService {
    PatientMedicalData create(PatientMedicalData patientMedicalData) throws ResourceNotFoundException;
    PatientMedicalData update(PatientMedicalData patientMedicalData) throws ResourceNotFoundException;
    Long deleteById(Long id) throws ResourceNotFoundException;
    List<PatientMedicalData> selectAll();
    Optional<PatientMedicalData> selectPatientMedicalDataById(Long id) throws ResourceNotFoundException;
}