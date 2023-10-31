package com.digitalmedia.users.dto;

import com.digitalmedia.users.model.UserType;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class NewUserDto {
    private final String name;

    private final String surname;

    private final String username;

    private final String email;

    private final String password;

    private final UserType type;

    @JsonCreator
    public NewUserDto(String name, String surname, String username, String email, String password, UserType type) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }
}