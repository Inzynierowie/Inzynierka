package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.model.Price;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient create(Patient patient);
    Patient update(Patient patient);
    List<Patient> selectAll();
    Patient selectById(Long id);
    Optional<Patient> selectByUserId(Long userId);
}