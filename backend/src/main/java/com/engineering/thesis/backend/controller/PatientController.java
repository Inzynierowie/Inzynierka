package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.controller.ExceptionHandlers.PatientExceptions.PatientNotFoundException;
import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/patient")
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/select/{id}")
    public Patient selectPatientById(@PathVariable Long id) {
        try {
            Patient patient = patientService.selectPatientById(id);
            return patient;
        }
        catch (Exception e){
            throw new PatientNotFoundException(id);
        }
    }

    @GetMapping("/select")
    public List<Patient> selectAll() {
        return patientService.selectAll();
    }
}