package com.integrationninjas.springbootexample.controller;

import java.util.List;
import com.integrationninjas.springbootexample.dto.PatientDto;
import com.integrationninjas.springbootexample.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/patient")  // Base path for patient-related endpoints
public class PatientController {

    @Autowired
    private PatientService patientService;

    
    @GetMapping("/patients")  
    public ResponseEntity<List<PatientDto>> getPatients() {
        List<PatientDto> patientsList = patientService.getPatients();
        return new ResponseEntity<>(patientsList, HttpStatus.OK);
    }

    
    @PostMapping("/patients")  
    public ResponseEntity<String> createPatient(@RequestBody PatientDto patientDto) {
        try {
            String status = patientService.createPatient(patientDto);
            return new ResponseEntity<>(status, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
