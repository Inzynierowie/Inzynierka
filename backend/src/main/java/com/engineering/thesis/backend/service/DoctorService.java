package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Doctor;

import java.util.List;

public interface DoctorService {
    void create(Doctor doctor);

    void deleteById(Long id);

    List<Doctor> selectAll();

    Doctor selectDoctorById(Long id);
}