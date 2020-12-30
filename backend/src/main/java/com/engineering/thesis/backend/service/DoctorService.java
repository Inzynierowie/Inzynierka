package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Doctor create(Doctor doctor);

    Doctor update(Doctor doctor);

    List<Doctor> selectAll();

    Doctor selectById(Long id);

    Optional<Doctor> selectByUserId(Long userId);
}