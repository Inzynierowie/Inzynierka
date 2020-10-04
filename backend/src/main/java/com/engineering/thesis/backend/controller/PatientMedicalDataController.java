package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.PatientMedicalData;
import com.engineering.thesis.backend.service.PatientMedicalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patientMedicalData")
public class PatientMedicalDataController {

    @Autowired
    private PatientMedicalDataService patientMedicalDataService;

    @PostMapping("/create")
    public void create(@RequestBody PatientMedicalData patientMedicalData){
        patientMedicalDataService.create(patientMedicalData);
    }

    @GetMapping("/select/{id}")
    public PatientMedicalData selectPatientMedicalDataById(@PathVariable Long id) {
        return patientMedicalDataService.selectPatientMedicalDataById(id);
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
