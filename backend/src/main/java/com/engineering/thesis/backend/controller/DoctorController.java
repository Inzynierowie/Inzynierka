package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/create")
    public void create(Doctor doctor){
        doctorService.create(doctor);
    }

    @GetMapping("/select/{id}")
    public Doctor selectDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.selectDoctorById(id);
        return doctor;
    }

    @GetMapping("/select")
    public List<Doctor> selectAll(){
        return doctorService.selectAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        doctorService.deleteById(id);
    }
}