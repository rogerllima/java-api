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
        client.setAttendanceHour(body.getAttendanceHour());
        client.setAttendanceDate(body.getAttendanceDate());
        clientRepository.save(body);
        return ResponseEntity.ok(new RegisterClientDTO(body.getName(), body.getAttendanceHour(), body.getAttendanceDate()));
    }

    @GetMapping("/list-attendances")
    public ResponseEntity listAllClients(@RequestParam String date){
        if(date.isEmpty()){
            return ResponseEntity.ok(clientRepository.findAll());
        }
        return ResponseEntity.ok(clientRepository.findByAttendanceDateOrderByAttendanceHour(date));
    }

    @GetMapping("/list-confirmed")
    public ResponseEntity listConfirmedClients(){
        return ResponseEntity.ok(clientRepository.findByConfirmClientTrue());
    }

    @GetMapping("/find")
    public ResponseEntity findClient(@RequestParam String name){
        return ResponseEntity.ok(clientRepository.findByNameIgnoreCaseContaining(name));
    }

    @PutMapping("/confirm")
    public ResponseEntity updateConfirmClient(@RequestBody Client data){
       Client client = clientRepository.getReferenceById(data.getId());
       client.setConfirmClient(data.getConfirmClient());
       clientRepository.save(client);
       return ResponseEntity.ok("Attendance of client was updated");
    }
}
