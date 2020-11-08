package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/select/{id}")
    public Patient selectPatientById(@PathVariable Long id) {
        return patientService.selectPatientById(id);
    }

    @GetMapping("/select")
    public List<Patient> selectAll() {
        return patientService.selectAll();
    }
}