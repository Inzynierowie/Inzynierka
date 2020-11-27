package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Doctor;

import java.util.List;

public interface DoctorService {
    void create(Doctor doctor);
    List<Doctor> selectAll();
    Doctor selectById(Long id);
    Doctor selectByUserId(Long userId);
}