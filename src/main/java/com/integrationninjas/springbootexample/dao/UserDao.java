package com.integrationninjas.springbootexample.dao;

import com.integrationninjas.springbootexample.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient, Long> {
}
