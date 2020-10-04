package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.model.MedicalFacility;
import com.engineering.thesis.backend.repository.MedicalFacilityRepository;
import com.engineering.thesis.backend.service.MedicalFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalFacilityServiceImpl implements MedicalFacilityService {

    @Autowired
    private MedicalFacilityRepository medicalFacilityRepository;

    @Override
    public void create(MedicalFacility medicalFacility) {
        medicalFacilityRepository.save(medicalFacility);
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
    public MedicalFacility selectMedicalFacilityById(Long id) {
        return medicalFacilityRepository.findById(id).get();
    }
}
