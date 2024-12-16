package com.integrationninjas.appointmentschedulingservice.controller;

import com.integrationninjas.appointmentschedulingservice.dto.AppointmentDto;
import com.integrationninjas.appointmentschedulingservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        List<AppointmentDto> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentDto appointmentDto) {
        try {
            String status = appointmentService.bookAppointment(appointmentDto);
            return new ResponseEntity<>(status, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
