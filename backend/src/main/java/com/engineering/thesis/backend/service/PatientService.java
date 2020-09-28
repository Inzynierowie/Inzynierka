package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Patient;
import java.util.List;
import java.util.Optional;

public interface PatientService {
    void create(Patient patient);
    void deleteById(Long id);
    List<Patient> selectAll();
    Patient selectPatientById(Long id);

}
