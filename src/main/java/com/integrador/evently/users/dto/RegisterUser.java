package com.integrador.evently.users.dto;

import com.integrador.evently.users.model.UserType;
import lombok.Data;

@Data
public class RegisterUser {
    private String email;
    private String firstname;
    private String lastname;
    private UserType type;
    private String password;
}
