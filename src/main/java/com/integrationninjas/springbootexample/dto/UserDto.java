package com.integrationninjas.springbootexample.dto;

import lombok.*;

@Data
public class PatientDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String medicalHistory;
    private String prescriptions;
}
