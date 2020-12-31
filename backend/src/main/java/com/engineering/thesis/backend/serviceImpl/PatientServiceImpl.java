package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.repository.PatientRepository;
import com.engineering.thesis.backend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public Patient create(Patient patient) throws ResourceNotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(patient.getId());
        if (patientOptional.isPresent()) {
            throw new ResourceNotFoundException("User with Id " + patient.getId() + " already exists");
        }
        return patientRepository.save(patient);
    }

    @Override
    public Patient update(Patient patient) throws ResourceNotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(patient.getId());
        if (patientOptional.isEmpty()) {
            throw new ResourceNotFoundException("Patient with Id " + patient.getId() + " doesn't  exists");
        }
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> selectAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient selectById(Long id) throws ResourceNotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            throw new ResourceNotFoundException("Patient with Id " + id + " doesn't  exists");
        }
        return patientRepository.findById(id).get();
    }

    @Override
    public Optional<Patient> selectByUserId(Long userId) throws ResourceNotFoundException {
        Optional<Patient> patientOptional = patientRepository.findByUserId(userId);
        if (patientOptional.isEmpty()) {
            throw new ResourceNotFoundException("User with Id " + userId + " doesn't  exists");
        }
        return patientRepository.findByUserId(userId);
    }
}