package com.marechalrf.marechalrfback.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class UserRole {
    @EmbeddedId
    private UserRoleKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

    private LocalDate assignedDate;

    // Getters and Setters
}

@Embeddable
public class UserRoleKey implements Serializable {
    private Long userId;
    private Long roleId;

    // hashCode and equals
}
