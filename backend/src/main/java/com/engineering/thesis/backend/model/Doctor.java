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

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_user") )
    private User user;

    private String specialist;

}