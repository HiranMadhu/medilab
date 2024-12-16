package com.integrationninjas.springbootexample.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private Long id;
    private String recipientEmail;
    private String message;
    private String notificationType; // e.g., Reminder or Follow-up
    private String status; // e.g., Sent, Pending, Failed
}
