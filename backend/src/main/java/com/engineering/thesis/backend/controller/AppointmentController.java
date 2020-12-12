package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.Appointment;
import com.engineering.thesis.backend.model.MedicalFacility;
import com.engineering.thesis.backend.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/create")
    public void create(@RequestBody Appointment appointment) {
        appointmentService.create(appointment);
    }

    @PutMapping("/update")
    public void update(@RequestBody Appointment appointment) {
        appointmentService.update(appointment);
    }

    @GetMapping("/select/{id}")
    public Optional<Appointment> selectAppointmentById(@PathVariable Long id) {
        return appointmentService.selectAppointmentById(id);
    }

    @GetMapping("/select")
    public List<Appointment> selectAll() {
        return appointmentService.selectAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        appointmentService.deleteById(id);
    }
}