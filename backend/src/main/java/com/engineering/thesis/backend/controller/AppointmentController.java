package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.Appointment;
import com.engineering.thesis.backend.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/create")
    public void create(Appointment appointment){
        appointmentService.create(appointment);
    }

    @GetMapping("/select/{id}")
    public Appointment selectAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.selectAppointmentById(id);
        return appointment;
    }

    @GetMapping("/select")
    public List<Appointment> selectAll(){
        return appointmentService.selectAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        appointmentService.deleteById(id);
    }
}
