package com.example.login_auth_api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserDTO (
        @NotBlank(message = "Invalid Name: Empty name")
        @NotNull(message = "Invalid Name: Name is NULL")
        String name,

        @NotBlank(message = "Invalid password: Empty password")
        @NotNull(message = "Invalid password: password is NULL")
        String password,

        @NotBlank(message = "Invalid email: Empty email")
        @NotNull(message = "Invalid email: email is NULL")
        @Email(message = "Invalid email: invalid format")
        String email) {
}
