package com.aa.order_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aa.order_service.dto.ApiError;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ApiError> handleProductNotFound(ProductNotFoundException ex, HttpServletRequest req) {
		return build(HttpStatus.NOT_FOUND, "Product not found", ex.getMessage(), req.getRequestURI());
	}

	@ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<ApiError> handleStockException(InsufficientStockException ex, HttpServletRequest req) {
		return build(HttpStatus.BAD_REQUEST, "Insufficient stock", ex.getMessage(), req.getRequestURI());
	}
	
	@ExceptionHandler(ProductServiceUnavailableException.class)
	public ResponseEntity<ApiError> handleProductServiceUnavailableException(ProductServiceUnavailableException ex, HttpServletRequest req) {
		return build(HttpStatus.SERVICE_UNAVAILABLE, "Product Service Unavailable", ex.getMessage(),
				req.getRequestURI());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleOther(Exception ex, HttpServletRequest req) {
		return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error", "Unexpected error occurred",
				req.getRequestURI());
	}

	private ResponseEntity<ApiError> build(HttpStatus status, String error, String message, String path) {
		return ResponseEntity.status(status)
				.body(new ApiError(LocalDateTime.now(), status.value(), error, message, path));
	}

}
