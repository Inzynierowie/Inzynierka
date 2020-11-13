package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.model.PatientMedicalData;
import com.engineering.thesis.backend.repository.PatientMedicalDataRepository;
import com.engineering.thesis.backend.service.PatientMedicalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientMedicalDataServiceImpl implements PatientMedicalDataService {
    private final PatientMedicalDataRepository patientMedicalDataRepository;

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
        return patientMedicalDataRepository.findById(id).get();
    }
}