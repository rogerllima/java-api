package com.example.login_auth_api.domain.schedules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="schedules")
@Table(name="schedules")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Boolean confirmClient;
    private String attendanceDate;
    private String attendanceHour;
}
