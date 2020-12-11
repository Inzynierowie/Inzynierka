package com.engineering.thesis.backend.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity(name = "medicalFacility")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
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
    @NotBlank(message = "Phone can't be empty")
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]{8,}$",
            message = "Phone number is invalid")
    private String contactNumber;
}