package com.integrationninjas.springbootexample.service;

import com.integrationninjas.springbootexample.dto.PatientDto;
import java.util.List;

public interface PatientService {

    String createPatient(PatientDto patientDto);

    List<PatientDto> getPatients();
}
