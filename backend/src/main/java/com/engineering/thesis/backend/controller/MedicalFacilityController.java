package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.MedicalFacility;
import com.engineering.thesis.backend.service.MedicalFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/medicalFacility")
public class MedicalFacilityController {
    private final MedicalFacilityService medicalFacilityService;

    @PostMapping("/create")
    public void create(@RequestBody MedicalFacility medicalFacility) {
        medicalFacilityService.create(medicalFacility);
    }

    @PutMapping("/update")
    public void update(@RequestBody MedicalFacility medicalFacility) {
        medicalFacilityService.update(medicalFacility);
    }

    @GetMapping("/select/{id}")
    public Optional<MedicalFacility> selectMedicalFacilityById(@PathVariable Long id) {
        return medicalFacilityService.selectMedicalFacilityById(id);
    }

    @GetMapping("/select")
    public List<MedicalFacility> selectAll() {
        return medicalFacilityService.selectAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        medicalFacilityService.deleteById(id);
    }
}