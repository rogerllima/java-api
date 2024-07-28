package com.example.login_auth_api.services;

import com.example.login_auth_api.domain.schedules.Schedules;
import com.example.login_auth_api.dto.schedules.ConfirmClientDTO;
import com.example.login_auth_api.dto.schedules.SchedulesRequestDTO;
import com.example.login_auth_api.repositories.SchedulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Service
public class SchedulesService {
    private final SchedulesRepository schedulesRepository ;

    public ResponseEntity listAllClients(@RequestParam String date){
        if(date.isEmpty()){
            return ResponseEntity.ok(schedulesRepository.findAll());
        }
        return ResponseEntity.ok(schedulesRepository.findByAttendanceDateOrderByAttendanceHour(date));
    }

    public ResponseEntity createNewClient(@RequestBody SchedulesRequestDTO body ){
        Schedules schedules = new Schedules();
        schedules.setId(schedules.getId());
        schedules.setIdClient(body.idClient());
        schedules.setConfirmClient(body.confirmClient());
        schedules.setAttendanceHour(body.attendanceHour());
        schedules.setAttendanceDate(body.attendanceDate());
        schedulesRepository.save(schedules);
        return ResponseEntity.ok("Scheduled client!");
    }

    public ResponseEntity listConfirmedClients(){
        return ResponseEntity.ok(schedulesRepository.findByConfirmClientTrue());
    }

    public ResponseEntity updateConfirmClient(@RequestBody ConfirmClientDTO data){
        Schedules schedules = schedulesRepository.getReferenceById(data.id());
        schedules.setConfirmClient(data.confirmClient());
        schedulesRepository.save(schedules);
        return ResponseEntity.ok("Attendance of client was updated");
    }
}
