package com.marechalrf.marechalrfback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    private String nom;
    @NotBlank
    @NotNull
    private String prenom;
    @NotBlank
    @NotNull
    private String entreprise;
    @NotBlank
    @NotNull
    private String fonction;
    @NotBlank
    @NotNull
    @Email
    private String email;
    private String telephone;
    private String message;
    @NotNull
    private boolean consent;

}
