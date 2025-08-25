package com.aa.notification_service.event;

import java.math.BigDecimal;

public record OrderPlacedEvent(
		Long orderId,
		String productName,
		int quantity,
		BigDecimal totalPrice) {}
