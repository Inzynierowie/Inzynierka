package com.engineering.thesis.backend.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "medicalFacility")
public class MedicalFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
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
