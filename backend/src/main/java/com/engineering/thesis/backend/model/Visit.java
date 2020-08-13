package com.engineering.thesis.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;

@Data
@Entity
public class Visit {

    @Id
    private int id;
    private String doctorName;
    private String doctorSurname;
    private String patientName;
    private String patientSurname;
    private long cost;
    private Calendar date;
}