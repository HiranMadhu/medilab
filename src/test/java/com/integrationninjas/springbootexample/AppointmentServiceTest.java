package com.integrationninjas.appointmentschedulingservice.service.impl.appointmentserviceimpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentServiceTest {

    private final AppointmentService appointmentService = new AppointmentService();

    @Test
    public void testScheduleAppointmentSuccess() {
        // Arrange
        String patientName = "John Doe";
        String doctorName = "Dr. Smith";
        String time = "2024-12-20 10:00 AM";

        // Act
        boolean result = appointmentService.scheduleAppointment(patientName, doctorName, time);

        // Assert
        assertTrue(result, "Appointment should be scheduled successfully.");
    }

    @Test
    public void testScheduleAppointmentWithNullData() {
        // Arrange
        String patientName = null;
        String doctorName = "Dr. Smith";
        String time = "2024-12-20 10:00 AM";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> appointmentService.scheduleAppointment(patientName, doctorName, time),
            "Exception should be thrown for invalid input");
    }
}
