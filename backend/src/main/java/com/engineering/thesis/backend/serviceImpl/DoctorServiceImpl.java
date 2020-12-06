package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.repository.DoctorRepository;
import com.engineering.thesis.backend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public void create(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> selectAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor selectById(Long id) {
        return doctorRepository.findById(id).get();
    }

    @Override
    public Doctor selectByUserId(Long userId) {
        return doctorRepository.findByUserId(userId);
    }
}