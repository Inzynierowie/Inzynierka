package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/select/{id}")
    public Doctor selectDoctorById(@PathVariable Long id) {
        return doctorService.selectDoctorById(id);
    }

    @GetMapping("/select")
    public List<Doctor> selectAll() {
        return doctorService.selectAll();
    }
}