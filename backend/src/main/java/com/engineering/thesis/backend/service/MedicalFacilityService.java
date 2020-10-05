package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.MedicalFacility;

import java.util.List;

public interface MedicalFacilityService {
    void create(MedicalFacility medicalFacility);
    void deleteById(Long id);
    List<MedicalFacility> selectAll();
    MedicalFacility selectMedicalFacilityById(Long id);
}
