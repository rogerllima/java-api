package com.example.login_auth_api.services;

import com.example.login_auth_api.domain.client.Client;
import com.example.login_auth_api.dto.client.RegisterClientDTO;
import com.example.login_auth_api.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ResponseEntity createNewClient(RegisterClientDTO body ){
        Client client = new Client();
        client.setName(body.name());
        client.setPhone(body.phone());
        clientRepository.save(client);
        return ResponseEntity.ok(new RegisterClientDTO(client.getName(), client.getPhone()));
    }

    public ResponseEntity findClient(@RequestParam String name){
        return ResponseEntity.ok(clientRepository.findByNameIgnoreCaseContaining(name));
    }
}
