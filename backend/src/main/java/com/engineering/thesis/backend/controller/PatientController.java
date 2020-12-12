package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/patient")
public class PatientController {
    private final PatientService patientService;

    @PutMapping("/update")
    public void update(@RequestBody Patient patient) {
        patientService.update(patient);
    }

    @GetMapping("/select/{id}")
    public Patient selectById(@PathVariable Long id) { return patientService.selectById(id); }

    @GetMapping("/select")
    public List<Patient> selectAll() {
        return patientService.selectAll();
    }
}