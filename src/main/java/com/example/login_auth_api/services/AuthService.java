package com.example.login_auth_api.services;

import com.example.login_auth_api.domain.user.User;
import com.example.login_auth_api.dto.login.LoginRequestDTO;
import com.example.login_auth_api.dto.login.ResponseLoginDTO;
import com.example.login_auth_api.dto.user.RegisterUserDTO;
import com.example.login_auth_api.errorHandler.RestErrorMessage;
import com.example.login_auth_api.errorHandler.UserNotFoundException;
import com.example.login_auth_api.infra.security.TokenService;
import com.example.login_auth_api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseEntity login(LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new UserNotFoundException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseLoginDTO(token));
        }
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, "Email or Password were incorrect");
        return ResponseEntity.badRequest().body(threatResponse);
    }


    public ResponseEntity register(RegisterUserDTO body){
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
