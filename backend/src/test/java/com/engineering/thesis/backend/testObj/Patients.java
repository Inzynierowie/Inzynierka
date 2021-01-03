package com.engineering.thesis.backend.testObj;

import com.engineering.thesis.backend.model.Patient;

public class Patients {

    public static Patient patientNull = Patient.builder()
            .id(null)
            .user(Users.userPatient1)
            .isInsured(true)
            .build();

    public static Patient patient1 = Patient.builder()
            .id(1L)
            .user(Users.userPatient1)
            .isInsured(true)
            .build();

    public static Patient patient2 = Patient.builder()
            .id(2L)
            .user(Users.userPatient2)
            .isInsured(true)
            .build();

    public static Patient patient3 = Patient.builder()
            .id(3L)
            .user(Users.userPatient3)
            .isInsured(true)
            .build();
}
