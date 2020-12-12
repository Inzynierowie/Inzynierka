package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.model.Appointment;
import com.engineering.thesis.backend.model.MedicalFacility;
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
    public Appointment create(Appointment appointment) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointment.getId());
        if(appointmentOptional.isPresent()) {
            throw new CreateObjException("Appointment with Id "+ appointment.getId()+" already exists");
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<Appointment> selectAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> selectAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }
}