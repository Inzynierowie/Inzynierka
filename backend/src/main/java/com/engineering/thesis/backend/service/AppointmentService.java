package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment create(Appointment appointment);

    Appointment update(Appointment appointment);

    Long deleteById(Long id);

    List<Appointment> selectAll();

    Optional<Appointment> selectAppointmentById(Long id);
}