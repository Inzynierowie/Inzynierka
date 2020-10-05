package com.engineering.thesis.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String name;
    private String surname;
    private boolean insured;

}
