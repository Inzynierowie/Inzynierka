package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.PatientMedicalData;
import com.engineering.thesis.backend.service.PatientMedicalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/patientMedicalData")
public class PatientMedicalDataController {
    private final PatientMedicalDataService patientMedicalDataService;

    @PostMapping("/create")
    public PatientMedicalData create(@RequestBody PatientMedicalData patientMedicalData) throws ResourceNotFoundException {
        return patientMedicalDataService.create(patientMedicalData);
    }

    @PutMapping("/update")
    public PatientMedicalData update(@RequestBody PatientMedicalData patientMedicalData) throws ResourceNotFoundException {
        return patientMedicalDataService.update(patientMedicalData);
    }

    @GetMapping("/select/{id}")
    public Optional<PatientMedicalData> selectPatientMedicalDataById(@PathVariable Long id) throws ResourceNotFoundException {
        return patientMedicalDataService.selectPatientMedicalDataById(id);
    }

    @GetMapping("/select")
    public List<PatientMedicalData> selectAll() {
        return patientMedicalDataService.selectAll();
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteById(@PathVariable Long id) throws ResourceNotFoundException { return patientMedicalDataService.deleteById(id); }
}