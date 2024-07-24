package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.client.Client;
import com.example.login_auth_api.dto.client.RegisterClientDTO;
import com.example.login_auth_api.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientRepository clientRepository;

    @PostMapping("/create")
    public ResponseEntity createNewClient(@RequestBody Client body ){
        Client client = new Client();
        client.setName(body.getName());
        client.setPhone(body.getPhone());
        clientRepository.save(body);
        return ResponseEntity.ok(new RegisterClientDTO(body.getName(), body.getPhone()));
    }

    @GetMapping("/find")
    public ResponseEntity findClient(@RequestParam String name){
        return ResponseEntity.ok(clientRepository.findByNameIgnoreCaseContaining(name));
    }
}
