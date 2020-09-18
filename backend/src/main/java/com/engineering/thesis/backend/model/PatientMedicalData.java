package com.engineering.thesis.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "patientsMedData")
public class PatientMedicalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_patient"))
    private Patient patient;

    private Long doctorId;
    private LocalDateTime treatmentDate;
    private String medicalProcedure;
    private String additionalNotes;
}
