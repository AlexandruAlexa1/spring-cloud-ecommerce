package com.aa.order_service.dto;

import java.time.LocalDateTime;

public record ApiError(
		LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path) {}
