package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.client.Client;
import com.example.login_auth_api.domain.schedules.Schedules;
import com.example.login_auth_api.repositories.SchedulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class SchedulesController {
    private final SchedulesRepository schedulesRepository ;

    @GetMapping("/list-attendances")
    public ResponseEntity listAllClients(@RequestParam String date){
        if(date.isEmpty()){
            return ResponseEntity.ok(schedulesRepository.findAll());
        }
        return ResponseEntity.ok(schedulesRepository.findByAttendanceDateOrderByAttendanceHour(date));
    }

    @GetMapping("/list-confirmed")
    public ResponseEntity listConfirmedClients(){
        return ResponseEntity.ok(schedulesRepository.findByConfirmClientTrue());
    }

    @PutMapping("/confirm")
    public ResponseEntity updateConfirmClient(@RequestBody Schedules data){
        Schedules schedules = schedulesRepository.getReferenceById(data.getId());
        schedules.setConfirmClient(data.getConfirmClient());
        schedulesRepository.save(schedules);
        return ResponseEntity.ok("Attendance of client was updated");
    }
}
