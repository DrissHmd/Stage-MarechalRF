package com.marechalrf.marechalrfback.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.marechalrf.marechalrfback.model.Role;
import lombok.*;
import lombok.experimental.Accessors;
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();
}