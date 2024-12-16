package com.integrationninjas.springbootexample.service;

import com.integrationninjas.springbootexample.dto.NotificationDto;

public interface NotificationService {

    String sendNotification(NotificationDto notificationDto);

    NotificationDto getNotification(Long id);
}
