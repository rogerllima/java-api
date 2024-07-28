package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.schedules.Schedules;
import com.example.login_auth_api.dto.schedules.ConfirmClientDTO;
import com.example.login_auth_api.dto.schedules.SchedulesRequestDTO;
import com.example.login_auth_api.repositories.SchedulesRepository;
import com.example.login_auth_api.services.SchedulesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class SchedulesController {
    private final SchedulesService schedulesService ;

    @GetMapping("/list-attendances")
    public ResponseEntity listAllClients(@RequestParam String date){
        return schedulesService.listAllClients(date);
    }

    @PostMapping("/create")
    public ResponseEntity createNewClient(@RequestBody @Validated SchedulesRequestDTO body ){
        return schedulesService.createNewClient(body);
    }

    @GetMapping("/list-confirmed")
    public ResponseEntity listConfirmedClients(){
        return schedulesService.listConfirmedClients();
    }

    @PutMapping("/confirm")
    public ResponseEntity updateConfirmClient(@RequestBody @Validated ConfirmClientDTO data){
        return schedulesService.updateConfirmClient(data);
    }
}
