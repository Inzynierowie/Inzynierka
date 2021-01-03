package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.Appointment;
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
    public Appointment create(@RequestBody Appointment appointment) throws ResourceNotFoundException {
        return appointmentService.create(appointment);
    }

    @PutMapping("/update")
    public Appointment update(@RequestBody Appointment appointment) throws ResourceNotFoundException {
        return appointmentService.update(appointment);
    }

    @GetMapping("/select/{id}")
    public Optional<Appointment> selectAppointmentById(@PathVariable Long id) throws ResourceNotFoundException {
        return appointmentService.selectAppointmentById(id);
    }

    @GetMapping("/select")
    public List<Appointment> selectAll() {
        return appointmentService.selectAll();
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteById(@PathVariable Long id) throws ResourceNotFoundException { return appointmentService.deleteById(id); }
}