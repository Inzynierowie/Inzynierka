package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Patient;
import java.util.List;

public interface IPatientService {
    List<Patient> findAll();
}
