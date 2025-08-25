package com.aa.order_service.exception;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(Long id) {
		super("Product with ID " + id + " not found.");
	}
}
