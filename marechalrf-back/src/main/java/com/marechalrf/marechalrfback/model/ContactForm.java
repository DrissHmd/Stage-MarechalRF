package com.marechalrf.marechalrfback.model;

import lombok.Getter;

@Getter
public class ContactForm {
    private String nom;
    private String prenom;
    private String entreprise;
    private String fonction;
    private String email;
    private String telephone;
    private String message;
    private boolean consent;

}