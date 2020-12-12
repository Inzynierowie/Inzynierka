package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.repository.DoctorRepository;
import com.engineering.thesis.backend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public Doctor create(Doctor doctor) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctor.getId());
        if (doctorOptional.isPresent()) {
            throw new CreateObjException("User with Id " + doctor.getId() + " already exists");
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return doctorRepository.save(doctor);
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
    public Optional<Doctor> selectByUserId(Long userId) {
        return doctorRepository.findByUserId(userId);
    }
}