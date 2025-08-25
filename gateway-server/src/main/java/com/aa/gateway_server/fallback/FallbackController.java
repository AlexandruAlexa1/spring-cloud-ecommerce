package com.aa.gateway_server.fallback;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aa.gateway_server.error.ErrorResponse;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

	@GetMapping("/product")
	public Mono<ErrorResponse> productFallback() {
		return Mono.just(new ErrorResponse(
				"product-service",
				"Product service is currently unavailable. Please try later.",
				Instant.now().toString()));
	}
	
	@GetMapping("/order")
	public Mono<ErrorResponse> orderFallback() {
		return Mono.just(new ErrorResponse(
				"order-service",
				"Order service is currently unavailable. Please try later.",
				Instant.now().toString()));
	}
	
	@GetMapping("/notification")
	public Mono<ErrorResponse> notificationFallback() {
		return Mono.just(new ErrorResponse(
				"notification-service",
				"Notification service is currently unavailable. Please try later.",
				Instant.now().toString()));
	}
}
