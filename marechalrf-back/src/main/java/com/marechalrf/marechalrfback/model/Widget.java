package com.marechalrf.marechalrfback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Widget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String type;
    private String data;

    @ManyToOne
    @JoinColumn(name = "dashboard_id")
    private Dashboard dashboard;
}