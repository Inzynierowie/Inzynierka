package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.PatientMedicalData;
import com.engineering.thesis.backend.service.PatientMedicalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patientMedicalData")
public class PatientMedicalDataController {

    @Autowired
    PatientMedicalDataService patientMedicalDataService;

    @PostMapping("/create")
    public void create(PatientMedicalData patientMedicalData){
        patientMedicalDataService.create(patientMedicalData);
    }

    @GetMapping("/select/{id}")
    public PatientMedicalData selectPatientMedicalDataById(@PathVariable Long id) {
        PatientMedicalData patientMedicalData = patientMedicalDataService.selectPatientMedicalDataById(id);
        return patientMedicalData;
    }

    @GetMapping("/select")
    public List<PatientMedicalData> selectAll(){
        return patientMedicalDataService.selectAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        patientMedicalDataService.deleteById(id);
    }


}