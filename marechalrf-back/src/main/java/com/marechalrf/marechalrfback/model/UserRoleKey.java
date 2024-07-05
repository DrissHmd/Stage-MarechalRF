package com.marechalrf.marechalrfback.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserRoleKey implements Serializable {
    private Long userId;
    private Long roleId;

    // hashCode and equals
}
