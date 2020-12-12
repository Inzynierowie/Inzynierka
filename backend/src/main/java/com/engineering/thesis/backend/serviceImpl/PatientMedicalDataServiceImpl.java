package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.model.MedicalFacility;
import com.engineering.thesis.backend.model.PatientMedicalData;
import com.engineering.thesis.backend.repository.PatientMedicalDataRepository;
import com.engineering.thesis.backend.service.PatientMedicalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientMedicalDataServiceImpl implements PatientMedicalDataService {
    private final PatientMedicalDataRepository patientMedicalDataRepository;

    @Override
    public PatientMedicalData create(PatientMedicalData patientMedicalData) {
        Optional<PatientMedicalData> medFacOptional = patientMedicalDataRepository.findById(patientMedicalData.getId());
        if(medFacOptional.isPresent()) {
            throw new CreateObjException("PatientMedicalData with Id "+ patientMedicalData.getId()+" already exists");
        }
        return patientMedicalDataRepository.save(patientMedicalData);
    }

    @Override
    public PatientMedicalData update(PatientMedicalData patientMedicalData) {
        return patientMedicalDataRepository.save(patientMedicalData);
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
    public Optional<PatientMedicalData> selectPatientMedicalDataById(Long id) {
        return patientMedicalDataRepository.findById(id);
    }
}