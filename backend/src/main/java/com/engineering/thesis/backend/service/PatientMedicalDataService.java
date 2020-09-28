package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.model.PatientMedicalData;

import java.util.List;
import java.util.Optional;

public interface PatientMedicalDataService {
    void create(PatientMedicalData patientMedicalData);
    void deleteById(Long id);
    List<PatientMedicalData> selectAll();
    PatientMedicalData selectPatientMedicalDataById(Long id);
}
