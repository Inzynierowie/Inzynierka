package com.engineering.thesis.backend.repository;

import com.engineering.thesis.backend.model.MedicalFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalFacilityRepository extends JpaRepository<MedicalFacility, Long> {
}