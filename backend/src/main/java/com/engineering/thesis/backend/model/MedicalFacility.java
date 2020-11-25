package com.engineering.thesis.backend.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity(name = "medicalFacility")
public class MedicalFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @NotBlank(message = "Name can't be empty")
    private String name;
    @NotBlank(message = "Localization can't be empty")
    private String localization;
    @Range(min = 0, message = "Doctor count must be at least {min}")
    private long doctorCount;
    @Range(min = 0, message = "Patient count must be at least {min}")
    private long patientCount;
    @NotBlank(message = "Phone can't be empty")
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]{8,}$",
            message = "Phone number is invalid")
    private String contactNumber;
}