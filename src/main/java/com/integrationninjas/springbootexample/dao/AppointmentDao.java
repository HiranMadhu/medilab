package com.integrationninjas.appointmentschedulingservice.dao;

import com.integrationninjas.appointmentschedulingservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentDao extends JpaRepository<Appointment, Long> {
}
