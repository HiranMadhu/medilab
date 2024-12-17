package com.integrationninjas.appointmentschedulingservice.service.impl;

import com.integrationninjas.appointmentschedulingservice.dao.AppointmentDao;
import com.integrationninjas.appointmentschedulingservice.dto.AppointmentDto;
import com.integrationninjas.appointmentschedulingservice.entity.Appointment;
import com.integrationninjas.appointmentschedulingservice.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceTest {

    @Mock
    private AppointmentDao appointmentDao;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testBookAppointment() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setPatientName("John Doe");
        appointmentDto.setDoctorName("Dr. Smith");
        // Convert String to LocalDateTime
        appointmentDto.setAppointmentTime(LocalDateTime.parse("2024-12-17T10:00:00"));

        String result = appointmentService.bookAppointment(appointmentDto);

        assertEquals("Appointment booked successfully!", result);
        verify(appointmentDao, times(1)).save(any(Appointment.class)); // Verify save was called
    }

    @Test
    void testGetAllAppointments() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setPatientName("John Doe");
        appointment.setDoctorName("Dr. Smith");
        // Convert String to LocalDateTime
        appointment.setAppointmentTime(LocalDateTime.parse("2024-12-17T10:00:00"));

        when(appointmentDao.findAll()).thenReturn(List.of(appointment));

        List<AppointmentDto> appointments = appointmentService.getAllAppointments();

        assertNotNull(appointments);
        assertEquals(1, appointments.size());
        assertEquals("John Doe", appointments.get(0).getPatientName());
        assertEquals("Dr. Smith", appointments.get(0).getDoctorName());
    }
}
