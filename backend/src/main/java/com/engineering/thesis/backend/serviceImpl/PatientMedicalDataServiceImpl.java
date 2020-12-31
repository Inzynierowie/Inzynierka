package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
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
    public PatientMedicalData create(PatientMedicalData patientMedicalData) throws ResourceNotFoundException {
        Optional<PatientMedicalData> patientMedicalDataOptional = patientMedicalDataRepository.findById(patientMedicalData.getId());
        if (patientMedicalDataOptional.isPresent()) {
            throw new ResourceNotFoundException("PatientMedicalData with Id " + patientMedicalData.getId() + " already exists");
        }
        return patientMedicalDataRepository.save(patientMedicalData);
    }

    @Override
    public PatientMedicalData update(PatientMedicalData patientMedicalData) throws ResourceNotFoundException {
        Optional<PatientMedicalData> patientMedicalDataOptional = patientMedicalDataRepository.findById(patientMedicalData.getId());
        if (patientMedicalDataOptional.isEmpty()) {
            throw new ResourceNotFoundException("PatientMedicalData with Id " + patientMedicalData.getId() + " doesn't  exists");
        }
        return patientMedicalDataRepository.save(patientMedicalData);
    }

    @Override
    public Long deleteById(Long id) throws ResourceNotFoundException {
        Optional<PatientMedicalData> patientMedicalDataOptional = patientMedicalDataRepository.findById(id);
        if (patientMedicalDataOptional.isEmpty()) {
            throw new ResourceNotFoundException("PatientMedicalData with Id " + id + " doesn't  exists");
        }
        patientMedicalDataRepository.deleteById(id);
        return id;
    }

    @Override
    public List<PatientMedicalData> selectAll() {
        return patientMedicalDataRepository.findAll();
    }

    @Override
    public Optional<PatientMedicalData> selectPatientMedicalDataById(Long id) throws ResourceNotFoundException {
        Optional<PatientMedicalData> patientMedicalDataOptional = patientMedicalDataRepository.findById(id);
        if (patientMedicalDataOptional.isEmpty()) {
            throw new ResourceNotFoundException("PatientMedicalData with Id " + id + " doesn't  exists");
        }
        return patientMedicalDataRepository.findById(id);
    }
}