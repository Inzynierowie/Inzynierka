package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient create(Patient patient) throws ResourceNotFoundException;
    Patient update(Patient patient) throws ResourceNotFoundException;
    List<Patient> selectAll();
    Patient selectById(Long id) throws ResourceNotFoundException;
    Optional<Patient> selectByUserId(Long userId) throws ResourceNotFoundException;
}