package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.client.Client;
import com.example.login_auth_api.dto.client.RegisterClientDTO;
import com.example.login_auth_api.repositories.ClientRepository;
import com.example.login_auth_api.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity createNewClient(@RequestBody @Validated RegisterClientDTO body ){
        return clientService.createNewClient(body);
    }

    @GetMapping("/find")
    public ResponseEntity findClient(@RequestParam String name){
        return clientService.findClient(name);
    }
}
