package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.Appointment;
import com.engineering.thesis.backend.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/create")
    public void create(@RequestBody Appointment appointment) {
        appointmentService.create(appointment);
    }

    @GetMapping("/select/{id}")
    public Appointment selectAppointmentById(@PathVariable Long id) {
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