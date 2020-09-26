package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PatientService implements IPatientService{
    @Autowired
    ICityRepository repository;

    @Override
    public List<Patient> findAll() {

        var patients = (List<Patient>) repository.findAll();

        return patients;
    }
}
