package com.engineering.thesis.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;

@Data
@Entity
public class PatientMedicalData {

    @Id
    private int id;
    private int patientId;
    private String patientName;
    private String patientSurname;
    private Calendar date;
    private String medicalProcedure;
    private String doctorPerformingSurgery;
}
