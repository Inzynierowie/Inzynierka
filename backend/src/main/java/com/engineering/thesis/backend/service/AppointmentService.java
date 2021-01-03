package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment create(Appointment appointment) throws ResourceNotFoundException;
    Appointment update(Appointment appointment) throws ResourceNotFoundException;
    Long deleteById(Long id) throws ResourceNotFoundException;
    List<Appointment> selectAll();
    Optional<Appointment> selectAppointmentById(Long id) throws ResourceNotFoundException;
}