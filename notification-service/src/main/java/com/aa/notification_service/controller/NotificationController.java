package com.aa.notification_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aa.notification_service.entity.Notification;
import com.aa.notification_service.service.NotificationService;

@RefreshScope
@RestController
@RequestMapping("/notifications")
public class NotificationController {

	private final NotificationService service;
	
	@Value("${custom.message:Default message}")
	private String message;
	
	public NotificationController(NotificationService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<Notification>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("/message")
    public String getMessage() {
    	return message;
    }
}
