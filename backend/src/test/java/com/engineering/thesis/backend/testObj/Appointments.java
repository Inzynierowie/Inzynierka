package com.engineering.thesis.backend.testObj;

import com.engineering.thesis.backend.model.Appointment;

import java.time.LocalDateTime;

public class Appointments {

    public static Appointment appointmentNull = Appointment.builder()
            .id(null)
            .patient(Patients.patient1)
            .doctor(Doctors.doctor1)
            .cost(1000L)
            .appointmentDate(LocalDateTime.now())
            .build();

    public static Appointment appointment1 = Appointment.builder()
            .id(1L)
            .patient(Patients.patient1)
            .doctor(Doctors.doctor1)
            .cost(1000L)
            .appointmentDate(LocalDateTime.now())
            .build();

    public static Appointment appointment2 = Appointment.builder()
            .id(2L)
            .patient(Patients.patient2)
            .doctor(Doctors.doctor2)
            .cost(1000L)
            .appointmentDate(LocalDateTime.now())
            .build();

    public static Appointment appointment3 = Appointment.builder()
            .id(3L)
            .patient(Patients.patient3)
            .doctor(Doctors.doctor3)
            .cost(1000L)
            .appointmentDate(LocalDateTime.now())
            .build();
}
