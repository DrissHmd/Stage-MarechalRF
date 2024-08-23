package com.marechalrf.marechalrfback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FormData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String entreprise;
    private String fonction;
    private String email;
    private String phone;
    private String besoinsFormation;
    private String sujetsAborder;
    private String objectifs;
    private String exigencesSpecifiques;
    private String typeFormation;
    private String effectif;
    private String duree;
    private String handicap;
    private String typeHandicap;
    private String amenagement;
    private String commentaires;

}
