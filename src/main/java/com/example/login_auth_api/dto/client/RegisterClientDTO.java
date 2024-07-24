package com.example.login_auth_api.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterClientDTO (
        @NotBlank(message = "Invalid name: Empty name")
        @NotNull(message = "Invalid name: name is NULL")
        String name,

        @NotBlank(message = "Invalid attendanceDate: Empty attendanceDate")
        @NotNull(message = "Invalid attendanceDate: attendanceDate is NULL")
        String attendanceDate,

        @NotBlank(message = "Invalid attendanceHour: Empty attendanceHour")
        @NotNull(message = "Invalid attendanceHour: attendanceHour is NULL")
        String attendanceHour
){
}
