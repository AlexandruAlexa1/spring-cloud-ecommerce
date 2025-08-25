package com.aa.order_service.dto;

import java.math.BigDecimal;

public record OrderResponse(
		Long id,
		String productName,
		Integer quantity,
		BigDecimal totalPrice) {}

