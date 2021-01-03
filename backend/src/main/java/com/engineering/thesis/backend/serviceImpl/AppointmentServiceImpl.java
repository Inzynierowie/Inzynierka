package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.Appointment;
import com.engineering.thesis.backend.repository.AppointmentRepository;
import com.engineering.thesis.backend.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment create(Appointment appointment) throws ResourceNotFoundException {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointment.getId());
        if (appointmentOptional.isPresent()) {
            throw new ResourceNotFoundException("Appointment with Id " + appointment.getId() + " already exists");
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) throws ResourceNotFoundException {
        Optional<Appointment> priceOptional = appointmentRepository.findById(appointment.getId());
        if (priceOptional.isEmpty()) {
            throw new ResourceNotFoundException("Appointment with Id " + appointment.getId() + " doesn't  exists");
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public Long deleteById(Long id) throws ResourceNotFoundException {
        Optional<Appointment> priceOptional = appointmentRepository.findById(id);
        if (priceOptional.isEmpty()) {
            throw new ResourceNotFoundException("Appointment with Id " + id + " doesn't  exists");
        }
        appointmentRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Appointment> selectAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> selectAppointmentById(Long id) throws ResourceNotFoundException {
        Optional<Appointment> priceOptional = appointmentRepository.findById(id);
        if (priceOptional.isEmpty()) {
            throw new ResourceNotFoundException("Appointment with Id " + id + " doesn't  exists");
        }
        return appointmentRepository.findById(id);
    }
}