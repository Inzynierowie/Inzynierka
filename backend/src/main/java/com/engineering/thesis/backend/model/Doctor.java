package com.engineering.thesis.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Doctor {

    @Id
    private int id;
    private String name;
    private String surname;
    private String specialist;
    private String possibleMeetingData;
    private String email;
    private String password;
}
