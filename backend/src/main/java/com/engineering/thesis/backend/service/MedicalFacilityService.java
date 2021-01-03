package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.MedicalFacility;

import java.util.List;
import java.util.Optional;

public interface MedicalFacilityService {
    MedicalFacility create(MedicalFacility medicalFacility) throws ResourceNotFoundException;
    MedicalFacility update(MedicalFacility medicalFacility) throws ResourceNotFoundException;
    Long deleteById(Long id) throws ResourceNotFoundException;
    List<MedicalFacility> selectAll();
    Optional<MedicalFacility> selectMedicalFacilityById(Long id) throws ResourceNotFoundException;
}