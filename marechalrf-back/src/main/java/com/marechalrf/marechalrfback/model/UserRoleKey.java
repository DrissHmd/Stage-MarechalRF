package com.marechalrf.marechalrfback.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UserRoleKey implements Serializable {
    private Long userId;
    private Long roleId;

    public UserRoleKey() {}

    public UserRoleKey(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserRoleKey that = (UserRoleKey) obj;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roleId, that.roleId);
    }
}
