package com.aa.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aa.notification_service.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
