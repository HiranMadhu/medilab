package com.integrationninjas.springbootexample.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String recipientEmail;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String notificationType; // e.g., Reminder or Follow-up

    private String status; // e.g., Sent, Pending, Failed
}
