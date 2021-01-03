package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.Doctor;
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
    public Doctor create(Doctor doctor) throws ResourceNotFoundException {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctor.getId());
        if (doctorOptional.isPresent()) {
            throw new ResourceNotFoundException("User with Id " + doctor.getId() + " already exists");
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) throws ResourceNotFoundException {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctor.getId());
        if (doctorOptional.isEmpty()) {
            throw new ResourceNotFoundException("Doctor with Id " + doctor.getId() + " doesn't  exists");
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> selectAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor selectById(Long id) throws ResourceNotFoundException {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isEmpty()) {
            throw new ResourceNotFoundException("Doctor with Id " + id + " doesn't  exists");
        }
        return doctorRepository.findById(id).get();
    }

    @Override
    public Optional<Doctor> selectByUserId(Long userId) throws ResourceNotFoundException {
        Optional<Doctor> doctorOptional = doctorRepository.findByUserId(userId);
        if (doctorOptional.isEmpty()) {
            throw new ResourceNotFoundException("User with Id " + userId + " doesn't  exists");
        }
        return doctorRepository.findByUserId(userId);
    }
}