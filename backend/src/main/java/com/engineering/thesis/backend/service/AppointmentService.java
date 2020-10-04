package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Appointment;

import java.util.List;

public interface AppointmentService {
    void create(Appointment appointment);
    void deleteById(Long id);
    List<Appointment> selectAll();
    Appointment selectAppointmentById(Long id);
}
