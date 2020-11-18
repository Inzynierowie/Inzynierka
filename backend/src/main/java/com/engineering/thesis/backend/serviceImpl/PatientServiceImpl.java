package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.repository.PatientRepository;
import com.engineering.thesis.backend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public void create(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> selectAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient selectPatientById(Long id) {
        return patientRepository.findById(id).get();
    }
}