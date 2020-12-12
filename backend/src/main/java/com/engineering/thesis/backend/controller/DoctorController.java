package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    @PutMapping("/update")
    public void update(@RequestBody Doctor doctor) {
        doctorService.update(doctor);
    }

    @GetMapping("/select/{id}")
    public Doctor selectById(@PathVariable Long id) {
        return doctorService.selectById(id);
    }

    @GetMapping("/select")
    public List<Doctor> selectAll() {
        return doctorService.selectAll();
    }
}