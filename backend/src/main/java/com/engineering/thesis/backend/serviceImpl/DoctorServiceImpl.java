package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.repository.DoctorRepository;
import com.engineering.thesis.backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public void create(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public List<Doctor> selectAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor selectDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).get();

        return doctor;
    }
}
