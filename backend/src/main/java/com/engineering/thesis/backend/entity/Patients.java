package com.engineering.thesis.backend.entity;
import javax.persistence.*;

@Entity
public class Patients {
    @GeneratedValue
    @Id
    private int Patient_Id;

    @Column
    private String Patient_Name;

    @Column
    private String Patient_Surname;

    @Column
    private boolean Is_Insured;

    public int getId() {
        return Patient_Id;
    }

    public void setId(int id) {
        this.Patient_Id = id;
    }

    public String getName() {
        return Patient_Name;
    }

    public void setName(String name) {
        this.Patient_Name = name;
    }

    public String getSurname() {
        return Patient_Surname;
    }

    public void setSurname(String surname) {
        this.Patient_Surname = surname;
    }

    public boolean getInsurance() {
        return Is_Insured;
    }

    public void setInsurance(boolean insurance) {
        this.Is_Insured = insurance;
    }


    public Patients withId(final int id) {
        this.Patient_Id = id;
        return this;
    }

    public Patients withName(final String name) {
        this.Patient_Name = name;
        return this;
    }

    public Patients withSurname(final String surname) {
        this.Patient_Surname = surname;
        return this;
    }

    public Patients withInsurance(final boolean insurance) {
        this.Is_Insured = insurance;
        return this;
    }


    @Override
    public String toString() {
        return "PatientEntity{" +
                "id=" + Patient_Id +
                ", name='" + Patient_Name + '\'' +
                ", surname='" + Patient_Surname + '\'' +
                ", Insured?=" + Is_Insured +
                '}';
    }


}

