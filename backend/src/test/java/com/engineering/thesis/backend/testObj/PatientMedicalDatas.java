package com.engineering.thesis.backend.testObj;

import com.engineering.thesis.backend.model.PatientMedicalData;

import java.time.LocalDateTime;

public class PatientMedicalDatas {

    public static PatientMedicalData patientMedicalDataNull = PatientMedicalData.builder()
            .id(null)
            .patient(Patients.patient1)
            .doctor(Doctors.doctor1)
            .treatmentDate(LocalDateTime.now())
            .medicalProcedure("Biopsy")
            .additionalNotes("Funny patient LOL like him tho")
            .build();

    public static PatientMedicalData patientMedicalData1 = PatientMedicalData.builder()
            .id(1L)
            .patient(Patients.patient1)
            .doctor(Doctors.doctor1)
            .treatmentDate(LocalDateTime.now())
            .medicalProcedure("Biopsy")
            .additionalNotes("Funny patient LOL like him tho")
            .build();

    public static PatientMedicalData patientMedicalData2 = PatientMedicalData.builder()
            .id(2L)
            .patient(Patients.patient2)
            .doctor(Doctors.doctor2)
            .treatmentDate(LocalDateTime.now())
            .medicalProcedure("Biopsy")
            .additionalNotes("Funny patient LOL like him tho")
            .build();

    public static PatientMedicalData patientMedicalData3 = PatientMedicalData.builder()
            .id(3L)
            .patient(Patients.patient3)
            .doctor(Doctors.doctor3)
            .treatmentDate(LocalDateTime.now())
            .medicalProcedure("Biopsy")
            .additionalNotes("Funny patient LOL like him tho")
            .build();
}
