package com.integrationninjas.springbootexample.service.impl;

import com.integrationninjas.springbootexample.dao.NotificationDao;
import com.integrationninjas.springbootexample.dto.NotificationDto;
import com.integrationninjas.springbootexample.entity.Notification;
import com.integrationninjas.springbootexample.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    @Override
    public String sendNotification(NotificationDto notificationDto) {
        try {
            Notification notification = new Notification();
            notification.setRecipientEmail(notificationDto.getRecipientEmail());
            notification.setMessage(notificationDto.getMessage());
            notification.setNotificationType(notificationDto.getNotificationType());
            notification.setStatus("Pending");

            // Save notification to DB (could be queued for actual sending)
            notificationDao.save(notification);

            // Simulate sending notification (in a real case, integrate with an email or SMS service)
            notification.setStatus("Sent");
            notificationDao.save(notification);

            return "Notification sent successfully!";
        } catch (Exception e) {
            return "Error sending notification: " + e.getMessage();
        }
    }

    @Override
    public NotificationDto getNotification(Long id) {
        return notificationDao.findById(id)
                .map(notification -> new NotificationDto(
                        notification.getId(),
                        notification.getRecipientEmail(),
                        notification.getMessage(),
                        notification.getNotificationType(),
                        notification.getStatus()
                ))
                .orElse(null);
    }
}
