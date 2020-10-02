package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.model.PatientMedicalData;
import com.engineering.thesis.backend.repository.PatientMedicalDataRepository;
import com.engineering.thesis.backend.service.PatientMedicalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientMedicalDataServiceImpl implements PatientMedicalDataService {

    @Autowired
    PatientMedicalDataRepository patientMedicalDataRepository;

    @Override
    public void create(PatientMedicalData patientMedicalData) {
        patientMedicalDataRepository.save(patientMedicalData);
    }

    @Override
    public void deleteById(Long id) {
        patientMedicalDataRepository.deleteById(id);
    }

    @Override
    public List<PatientMedicalData> selectAll() {
        return patientMedicalDataRepository.findAll();
    }

    @Override
    public PatientMedicalData selectPatientMedicalDataById(Long id) {
        PatientMedicalData patientMedicalData = patientMedicalDataRepository.findById(id).get();
        return patientMedicalData;
    }
}
