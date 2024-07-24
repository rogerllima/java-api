package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.user.User;
import com.example.login_auth_api.dto.login.LoginRequestDTO;
import com.example.login_auth_api.dto.user.RegisterUserDTO;
import com.example.login_auth_api.dto.login.ResponseLoginDTO;
import com.example.login_auth_api.errorHandler.RestErrorMessage;
import com.example.login_auth_api.errorHandler.UserNotFoundException;
import com.example.login_auth_api.infra.security.TokenService;
import com.example.login_auth_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new UserNotFoundException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseLoginDTO(token));
        }
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, "Email or Password were incorrect");
        return ResponseEntity.badRequest().body(threatResponse);
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterUserDTO body){
        Optional<User> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseLoginDTO(token));
        }
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, "This email is already saved");
        return ResponseEntity.badRequest().body(threatResponse);
    }
}