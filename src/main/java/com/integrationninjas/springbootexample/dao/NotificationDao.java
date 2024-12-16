package com.integrationninjas.springbootexample.dao;

import com.integrationninjas.springbootexample.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationDao extends JpaRepository<Notification, Long> {
}
