package com.engineering.thesis.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String name;
    private String surname;
    private String specialist;

}
