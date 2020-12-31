package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Doctor create(Doctor doctor) throws ResourceNotFoundException;
    Doctor update(Doctor doctor) throws ResourceNotFoundException;
    List<Doctor> selectAll();
    Doctor selectById(Long id) throws ResourceNotFoundException;
    Optional<Doctor> selectByUserId(Long userId) throws ResourceNotFoundException;
}