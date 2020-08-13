package com.engineering.thesis.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Patient {

    @Id
    private int id;
    private String name;
    private String surname;
    private boolean insured;
    private String email;
    private String password;
}
