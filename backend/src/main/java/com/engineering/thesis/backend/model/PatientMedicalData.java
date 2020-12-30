package com.engineering.thesis.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "patientsMedData", schema = "healthcare")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientMedicalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_patient"))
    @NotNull(message = "Patient can't be empty")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_doctor"))
    @NotNull(message = "Doctor can't be empty")
    private Doctor doctor;
    @NotNull(message = "Date can't be empty")
    private LocalDateTime treatmentDate;
    @NotBlank(message = "Medical procedure can't be empty")
    private String medicalProcedure;
    @NotBlank(message = "Additional notes can't be empty")
    private String additionalNotes;
}