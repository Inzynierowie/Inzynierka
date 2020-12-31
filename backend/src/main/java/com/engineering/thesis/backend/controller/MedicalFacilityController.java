package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
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
    public MedicalFacility create(@RequestBody MedicalFacility medicalFacility) throws ResourceNotFoundException {
        return medicalFacilityService.create(medicalFacility);
    }

    @PutMapping("/update")
    public MedicalFacility update(@RequestBody MedicalFacility medicalFacility) throws ResourceNotFoundException {
        return medicalFacilityService.update(medicalFacility);
    }

    @GetMapping("/select/{id}")
    public Optional<MedicalFacility> selectMedicalFacilityById(@PathVariable Long id) throws ResourceNotFoundException {
        return medicalFacilityService.selectMedicalFacilityById(id);
    }

    @GetMapping("/select")
    public List<MedicalFacility> selectAll() {
        return medicalFacilityService.selectAll();
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteById(@PathVariable Long id) throws ResourceNotFoundException { return medicalFacilityService.deleteById(id); }
}