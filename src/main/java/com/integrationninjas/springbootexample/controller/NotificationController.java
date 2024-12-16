package com.integrationninjas.springbootexample.controller;

import com.integrationninjas.springbootexample.dto.NotificationDto;
import com.integrationninjas.springbootexample.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationDto notificationDto) {
        try {
            String status = notificationService.sendNotification(notificationDto);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> getNotification(@PathVariable Long id) {
        NotificationDto notificationDto = notificationService.getNotification(id);
        if (notificationDto != null) {
            return new ResponseEntity<>(notificationDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
