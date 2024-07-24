package com.example.login_auth_api.repositories;

import com.example.login_auth_api.domain.schedules.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchedulesRepository extends JpaRepository<Schedules, String> {
    List<Schedules> findByAttendanceDateOrderByAttendanceHour(String date);

    List<Schedules> findByConfirmClientTrue();
}
