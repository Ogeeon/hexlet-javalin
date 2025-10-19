package org.example.hexlet.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class User {
    private Long id;

    private String name;
    private String email;
    private String password;

    public User(String userName, String userEmail, String userPassword) {
        this.name = userName;
        this.email = userEmail;
        this.password = userPassword;
    }
}
