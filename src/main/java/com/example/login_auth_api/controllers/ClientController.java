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
        client.setAttendance_hour(body.getAttendance_hour());
        client.setAttendance_date(body.getAttendance_date());
        this.clientRepository.save(body);
        return ResponseEntity.ok(new RegisterClientDTO(body.getName(), body.getAttendance_hour(), body.getAttendance_date()));
    }

    @GetMapping("/list-all")
    public ResponseEntity listAllClients(){
        return ResponseEntity.ok(this.clientRepository.findAll());
    }

    @GetMapping("/list-confirmed")
    public ResponseEntity listConfirmedClients(){
        return ResponseEntity.ok(this.clientRepository.findConfirmedClients());
    }

    @GetMapping("/find")
    public ResponseEntity findClient(@RequestParam String name){
        return ResponseEntity.ok(this.clientRepository.findByNameIgnoreCaseContaining(name));
    }

    @PutMapping("/confirm")
    public ResponseEntity updateConfirmClient(@RequestBody Client data){
       Client client = this.clientRepository.getReferenceById(data.getId());
       client.setConfirm_client(data.getConfirm_client());
       this.clientRepository.save(client);
       return ResponseEntity.ok("Status was updated");
    }
}
