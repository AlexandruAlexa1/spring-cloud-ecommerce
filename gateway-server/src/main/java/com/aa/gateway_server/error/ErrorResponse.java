package com.aa.gateway_server.error;

public record ErrorResponse(
		String service,
		String message,
		String timestamp
		) {}
