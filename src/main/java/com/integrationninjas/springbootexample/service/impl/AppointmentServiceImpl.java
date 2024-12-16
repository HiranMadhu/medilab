package com.integrationninjas.appointmentschedulingservice.service.impl;

import com.integrationninjas.appointmentschedulingservice.dao.AppointmentDao;
import com.integrationninjas.appointmentschedulingservice.dto.AppointmentDto;
import com.integrationninjas.appointmentschedulingservice.entity.Appointment;
import com.integrationninjas.appointmentschedulingservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;

    @Override
    public String bookAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setPatientName(appointmentDto.getPatientName());
        appointment.setDoctorName(appointmentDto.getDoctorName());
        appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
        appointmentDao.save(appointment);
        return "Appointment booked successfully!";
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        return appointmentDao.findAll().stream().map(appointment -> {
            AppointmentDto dto = new AppointmentDto();
            dto.setId(appointment.getId());
            dto.setPatientName(appointment.getPatientName());
            dto.setDoctorName(appointment.getDoctorName());
            dto.setAppointmentTime(appointment.getAppointmentTime());
            return dto;
        }).collect(Collectors.toList());
    }
}
