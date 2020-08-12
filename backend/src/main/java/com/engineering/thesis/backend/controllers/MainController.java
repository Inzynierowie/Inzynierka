package com.engineering.thesis.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.engineering.thesis.backend.entity.Patients;
import com.engineering.thesis.backend.repositories.PatientsRepository;

@Controller

public class MainController {
    @Autowired
    public PatientsRepository patientsRepository;

    @RequestMapping("/db")
    @ResponseBody
    public String testMethod() {
        StringBuilder response = new StringBuilder();
        

        for(Patients i: patientsRepository.findAll()) {
            response.append(i).append("<br>");
        }

        return response.toString();
    }
}
