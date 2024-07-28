package com.example.login_auth_api.dto.schedules;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConfirmClientDTO (
        @NotBlank(message = "Invalid name: Empty name")
        @NotNull(message = "Invalid name: name is NULL")
        String id,

        @NotBlank(message = "Invalid name: Empty name")
        @NotNull(message = "Invalid name: name is NULL")
        Boolean confirmClient
){
}
