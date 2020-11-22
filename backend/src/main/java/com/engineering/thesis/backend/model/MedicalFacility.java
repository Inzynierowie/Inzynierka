package com.engineering.thesis.backend.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "medicalFacility")
public class MedicalFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
//tak
    @NotBlank
    private String name;
    @NotBlank
    private String localization;
    @NotNull
    @Range(min = 0)
    private long doctorCount;
    @NotNull
    @Range(min = 0)
    private long patientCount;
    @NotNull
    @Range(min = 100000000)
    private long contactNumber;
}