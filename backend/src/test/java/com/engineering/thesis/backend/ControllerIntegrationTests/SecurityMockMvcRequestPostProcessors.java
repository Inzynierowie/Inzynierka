package com.engineering.thesis.backend.ControllerIntegrationTests;

import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.util.Hashtable;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

public class SecurityMockMvcRequestPostProcessors {

    public static RequestPostProcessor DoctorRole() {
        return user("Tom").roles("DOCTOR");
    }

    public static RequestPostProcessor PatientRole() {
        return user("Rob").roles("PATIENT");
    }

    public static RequestPostProcessor InvalidRole() {
        return user("Max").roles("InvalidRole");
    }

    public static Hashtable<String, RequestPostProcessor> RulesMap() {
        final Hashtable<String, RequestPostProcessor> rulesMap = new Hashtable<String, RequestPostProcessor>();
        rulesMap.put("Doctor", DoctorRole());
        rulesMap.put("Patient", PatientRole());
        rulesMap.put("Invalid", InvalidRole());
        return rulesMap;
    }
}
