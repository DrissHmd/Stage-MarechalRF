package com.marechalrf.marechalrfback.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    @Column(name = "rule")
    private Set<User> users = new HashSet<>();
}
