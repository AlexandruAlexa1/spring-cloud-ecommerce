package com.aa.notification_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aa.notification_service.entity.Notification;
import com.aa.notification_service.repository.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public void sendNotification(String to, String message) {
        repository.save(new Notification(to, message));

        System.out.println("🔔 Notification sent: " + message);
    }

	public List<Notification> getAll() {
		return repository.findAll();
	}
}

