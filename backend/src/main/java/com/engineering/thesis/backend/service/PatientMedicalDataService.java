package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.PatientMedicalData;

import java.util.List;

public interface PatientMedicalDataService {
    void create(PatientMedicalData patientMedicalData);
    void deleteById(Long id);
    List<PatientMedicalData> selectAll();
    PatientMedicalData selectPatientMedicalDataById(Long id);
}