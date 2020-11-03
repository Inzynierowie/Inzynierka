package com.engineering.thesis.backend.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Entity(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_patient"))
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_doctor"))
    private Doctor doctor;
    @NotNull
    @Range(min = 0)
    private long cost;
    @NotNull
    private LocalDateTime appointmentDate;
}