package com.engineering.thesis.backend.service;

import com.engineering.thesis.backend.model.Appointment;
import com.engineering.thesis.backend.model.PatientMedicalData;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    void create(Appointment appointment);
    void deleteById(Long id);
    List<Appointment> selectAll();
    Appointment selectAppointmentById(Long id);
}
