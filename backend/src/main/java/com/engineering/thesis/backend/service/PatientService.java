package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Patient;

import java.util.List;

public interface PatientService {
    void create(Patient patient);
    List<Patient> selectAll();
    Patient selectById(Long id);
    Patient selectByUserId(Long userId);
}