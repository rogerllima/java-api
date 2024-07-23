package com.example.login_auth_api.dto.user;

import lombok.NonNull;

public record RegisterUserDTO (@NonNull String name, @NonNull String password, @NonNull String email) {
}
