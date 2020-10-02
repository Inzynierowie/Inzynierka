package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/patient/create")
    public void create(@RequestBody Patient patient){
        patientService.create(patient);
    }

    @GetMapping("/patient/select/{id}")
    public Patient selectPatientById(@PathVariable Long id) {
        Patient patient = patientService.selectPatientById(id);
        return patient;
    }

    @GetMapping("/patient/select")
    public List<Patient> selectAll(){
        return patientService.selectAll();
    }

    @DeleteMapping("/patient/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        patientService.deleteById(id);
    }
}
