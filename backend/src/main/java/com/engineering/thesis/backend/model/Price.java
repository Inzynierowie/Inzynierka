package com.engineering.thesis.backend.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "priceList")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @NotBlank
    private String treatment;
    @NotNull
    @Range(min = 0)
    private Long price;
}