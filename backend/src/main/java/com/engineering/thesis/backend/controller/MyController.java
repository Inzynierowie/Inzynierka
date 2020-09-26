package com.engineering.thesis.backend.controller;


import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@ComponentScan("com.engineering.thesis.backend.service;")
@RestController
public class MyController {

    @Autowired
    PatientService patientService;

    @GetMapping("/patients")
    public List<Patient> findAll() {

        return patientService.findAll();
    }

}