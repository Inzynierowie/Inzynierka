package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.MedicalFacility;

import java.util.List;
import java.util.Optional;

public interface MedicalFacilityService {
    MedicalFacility create(MedicalFacility medicalFacility);

    MedicalFacility update(MedicalFacility medicalFacility);

    Long deleteById(Long id);

    List<MedicalFacility> selectAll();

    Optional<MedicalFacility> selectMedicalFacilityById(Long id);
}