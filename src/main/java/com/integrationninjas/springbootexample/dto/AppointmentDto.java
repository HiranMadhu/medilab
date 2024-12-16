package com.integrationninjas.appointmentschedulingservice.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentDto {
    private Long id;
    private String patientName;
    private String doctorName;
    private LocalDateTime appointmentTime;
}
