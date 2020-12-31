package com.engineering.thesis.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "priceList", schema = "healthcare")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @NotBlank(message = "Treatment can't be empty")
    private String treatment;
    @Range(min = 0, message = "Price must be at least {min}")
    private Long price;
}