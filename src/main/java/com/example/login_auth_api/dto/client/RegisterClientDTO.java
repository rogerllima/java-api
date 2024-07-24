package com.example.login_auth_api.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterClientDTO (
        @NotBlank(message = "Invalid name: Empty name")
        @NotNull(message = "Invalid name: name is NULL")
        String name,

        @NotBlank(message = "Invalid phone: Empty phone")
        @NotNull(message = "Invalid phone: phone is NULL")
        String phone
){
}
