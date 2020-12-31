package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.MedicalFacility;
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
    public MedicalFacility create(MedicalFacility medicalFacility) throws ResourceNotFoundException {
        Optional<MedicalFacility> medicalFacilityOptional = medicalFacilityRepository.findById(medicalFacility.getId());
        if (medicalFacilityOptional.isPresent()) {
            throw new ResourceNotFoundException("Medical Facility with Id " + medicalFacility.getId() + " already exists");
        }
        return medicalFacilityRepository.save(medicalFacility);
    }

    @Override
    public MedicalFacility update(MedicalFacility medicalFacility) throws ResourceNotFoundException {
        Optional<MedicalFacility> medicalFacilityOptional = medicalFacilityRepository.findById(medicalFacility.getId());
        if (medicalFacilityOptional.isEmpty()) {
            throw new ResourceNotFoundException("MedicalFacility with Id " + medicalFacility.getId() + " doesn't  exists");
        }
        return medicalFacilityRepository.save(medicalFacility);
    }

    @Override
    public Long deleteById(Long id) throws ResourceNotFoundException {
        Optional<MedicalFacility> medicalFacilityOptional = medicalFacilityRepository.findById(id);
        if (medicalFacilityOptional.isEmpty()) {
            throw new ResourceNotFoundException("MedicalFacility with Id " + id + " doesn't  exists");
        }
        medicalFacilityRepository.deleteById(id);
        return id;
    }

    @Override
    public List<MedicalFacility> selectAll() {
        return medicalFacilityRepository.findAll();
    }

    @Override
    public Optional<MedicalFacility> selectMedicalFacilityById(Long id) throws ResourceNotFoundException {
        Optional<MedicalFacility> medicalFacilityOptional = medicalFacilityRepository.findById(id);
        if (medicalFacilityOptional.isEmpty()) {
            throw new ResourceNotFoundException("MedicalFacility with Id " + id + " doesn't  exists");
        }
        return medicalFacilityRepository.findById(id);
    }
}