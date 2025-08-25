package com.aa.notification_service.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aa.notification_service.event.OrderPlacedEvent;
import com.aa.notification_service.service.NotificationService;

@Configuration
public class OrderPlacedEventConsumer {
	
	private final NotificationService notificationService;

	public OrderPlacedEventConsumer(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@Bean
	Consumer<OrderPlacedEvent> notification() {
		return event -> {
			String message = "Order received: " + event.productName();
			notificationService.sendNotification("client@example.com", message);
		};
	}
}
