package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.model.MedicalFacility;
import com.engineering.thesis.backend.model.Price;
import com.engineering.thesis.backend.repository.MedicalFacilityRepository;
import com.engineering.thesis.backend.service.MedicalFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicalFacilityServiceImpl implements MedicalFacilityService {
    private final MedicalFacilityRepository medicalFacilityRepository;

    @Override
    public MedicalFacility create(MedicalFacility medicalFacility) {
        Optional<MedicalFacility> medFacOptional = medicalFacilityRepository.findById(medicalFacility.getId());
        if(medFacOptional.isPresent()) {
            throw new CreateObjException("Medical Facility with Id "+ medicalFacility.getId()+" already exists");
        }
        return medicalFacilityRepository.save(medicalFacility);
    }

    @Override
    public MedicalFacility update(MedicalFacility medicalFacility) {
        return medicalFacilityRepository.save(medicalFacility);
    }

    @Override
    public void deleteById(Long id) {
        medicalFacilityRepository.deleteById(id);
    }

    @Override
    public List<MedicalFacility> selectAll() {
        return medicalFacilityRepository.findAll();
    }

    @Override
    public Optional<MedicalFacility> selectMedicalFacilityById(Long id) {
        return medicalFacilityRepository.findById(id);
    }
}