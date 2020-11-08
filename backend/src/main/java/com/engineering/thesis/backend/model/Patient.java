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

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Fk_user") )
    private User user;

    @Column(name = "insured")
    private boolean isInsured;

}