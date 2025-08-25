package com.aa.order_service.exception;

public class InsufficientStockException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientStockException(Long id) {
		super("Insufficient stock for product with ID " + id);
	}
}
