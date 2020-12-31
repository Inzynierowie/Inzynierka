package com.engineering.thesis.backend.controller;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
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
    public Doctor update(@RequestBody Doctor doctor) throws ResourceNotFoundException {
        return doctorService.update(doctor);
    }

    @GetMapping("/select/{id}")
    public Doctor selectById(@PathVariable Long id) throws ResourceNotFoundException {
        return doctorService.selectById(id);
    }

    @GetMapping("/select")
    public List<Doctor> selectAll() {
        return doctorService.selectAll();
    }
}