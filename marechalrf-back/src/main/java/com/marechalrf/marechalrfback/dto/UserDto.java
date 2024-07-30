package com.marechalrf.marechalrfback.dto;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
public class UserDto {
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String username;
    private String password;
    private Long roleId;
    private LocalDate assignedDate;
}