package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.MedicalFacility;
import com.engineering.thesis.backend.service.MedicalFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicalFacility")
public class MedicalFacilityController {

    @Autowired
    MedicalFacilityService medicalFacilityService;

    @PostMapping("/create")
    public void create(MedicalFacility medicalFacility){
        medicalFacilityService.create(medicalFacility);
    }

    @GetMapping("/select/{id}")
    public MedicalFacility selectMedicalFacilityById(@PathVariable Long id) {
        MedicalFacility medicalFacility = medicalFacilityService.selectMedicalFacilityById(id);
        return medicalFacility;
    }

    @GetMapping("/select")
    public List<MedicalFacility> selectAll(){
        return medicalFacilityService.selectAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        medicalFacilityService.deleteById(id);
    }
}