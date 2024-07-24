package com.example.login_auth_api.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(
        @NotBlank(message = "Invalid name: Empty name")
        @NotNull(message = "Invalid name: name is NULL")
        @Email(message = "Invalid email: invalid format")
        String email,

        @NotBlank(message = "Invalid password: Empty password")
        @NotNull(message = "Invalid password: password is NULL")
        String password) { }
