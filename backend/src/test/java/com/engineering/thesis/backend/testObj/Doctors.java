package com.engineering.thesis.backend.testObj;

import com.engineering.thesis.backend.model.Doctor;

public class Doctors {

    public static Doctor doctorNull = Doctor.builder()
            .id(null)
            .user(Users.userDoctor1)
            .specialist("Cardiology")
            .build();

    public static Doctor doctor1 = Doctor.builder()
            .id(1L)
            .user(Users.userDoctor1)
            .specialist("Cardiology")
            .build();

    public static Doctor doctor2 = Doctor.builder()
            .id(1L)
            .user(Users.userDoctor2)
            .specialist("Cardiology")
            .build();

    public static Doctor doctor3 = Doctor.builder()
            .id(1L)
            .user(Users.userDoctor3)
            .specialist("Cardiology")
            .build();
}
