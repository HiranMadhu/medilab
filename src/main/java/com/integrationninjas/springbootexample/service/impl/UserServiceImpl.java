package com.integrationninjas.springbootexample.service;

import com.integrationninjas.springbootexample.dao.PatientDao;
import com.integrationninjas.springbootexample.dto.PatientDto;
import com.integrationninjas.springbootexample.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;

    @Override
    public String createPatient(PatientDto patientDto) {
        try {
            Patient patient = new Patient();
            patient.setFirstName(patientDto.getFirstName());
            patient.setLastName(patientDto.getLastName());
            patient.setEmail(patientDto.getEmail());
            patient.setMedicalHistory(patientDto.getMedicalHistory());
            patient.setPrescriptions(patientDto.getPrescriptions());

            patientDao.save(patient);
            return "Patient created successfully!";
        } catch (Exception e) {
            return "Error while creating patient: " + e.getMessage();
        }
    }

    @Override
    public List<PatientDto> getPatients() {
        List<Patient> patients = patientDao.findAll();
        return patients.stream()
                .map(patient -> new PatientDto(patient.getId(), patient.getFirstName(), patient.getLastName(), patient.getEmail(), patient.getMedicalHistory(), patient.getPrescriptions()))
                .collect(Collectors.toList());
    }
}
