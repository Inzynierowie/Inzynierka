package com.engineering.thesis.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MedicalFacility {

    @Id
    private int id;
    private String name;
    private String localization;
    private long doctorCount;
    private long patientCount;
    private long capital;
}
