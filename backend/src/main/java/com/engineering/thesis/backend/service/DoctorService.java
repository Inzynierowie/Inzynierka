package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.model.Patient;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    void create(Doctor doctor);
    void deleteById(Long id);
    List<Doctor> selectAll();
    Doctor selectDoctorById(Long id);
}
