package com.integrationninjas.springbootexample.service.impl;

import com.integrationninjas.springbootexample.dao.PatientDao;
import com.integrationninjas.springbootexample.dto.PatientDto;
import com.integrationninjas.springbootexample.entity.Patient;
import com.integrationninjas.springbootexample.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;

    @Override
    public String createPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setEmail(patientDto.getEmail());
        patient.setMedicalHistory(patientDto.getMedicalHistory());
        patient.setPrescriptions(patientDto.getPrescriptions());
        patientDao.saveAndFlush(patient);
        return "Patient Added Successfully";
    }

    @Override
    public List<PatientDto> getPatients() {
        List<Patient> patientsList = patientDao.findAll();
        List<PatientDto> dtoList = new ArrayList<>();
        if (!patientsList.isEmpty()) {
            patientsList.forEach(patient -> {
                PatientDto dto = new PatientDto();
                dto.setId(patient.getId());
                dto.setFirstName(patient.getFirstName());
                dto.setLastName(patient.getLastName());
                dto.setEmail(patient.getEmail());
                dto.setMedicalHistory(patient.getMedicalHistory());
                dto.setPrescriptions(patient.getPrescriptions());
                dtoList.add(dto);
            });
        }
        return dtoList;
    }
}
