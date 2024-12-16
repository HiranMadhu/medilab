package com.integrationninjas.appointmentschedulingservice.service;

import com.integrationninjas.appointmentschedulingservice.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    String bookAppointment(AppointmentDto appointmentDto);

    List<AppointmentDto> getAllAppointments();
}
