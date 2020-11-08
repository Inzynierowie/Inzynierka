package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/select/{id}")
    public Doctor selectDoctorById(@PathVariable Long id) {
        return doctorService.selectDoctorById(id);
    }

    @GetMapping("/select")
    public List<Doctor> selectAll() {
        return doctorService.selectAll();
    }
}