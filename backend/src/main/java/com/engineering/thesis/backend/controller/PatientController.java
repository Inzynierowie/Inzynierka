package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.controller.ExceptionHandlers.PatientExceptions.PatientNotFoundException;
import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    public void create(@RequestBody Patient patient){
        patientService.create(patient);
    }

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
    public List<Patient> selectAll(){
        return patientService.selectAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        patientService.deleteById(id);
    }
}
