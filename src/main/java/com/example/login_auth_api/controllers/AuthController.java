package com.example.login_auth_api.controllers;

import com.example.login_auth_api.dto.login.LoginRequestDTO;
import com.example.login_auth_api.dto.user.RegisterUserDTO;
import com.example.login_auth_api.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated LoginRequestDTO body){
        return authService.login(body);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterUserDTO body){
        return authService.register(body);
    }
}