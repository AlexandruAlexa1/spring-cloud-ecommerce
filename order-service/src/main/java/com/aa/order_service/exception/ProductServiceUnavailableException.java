package com.aa.order_service.exception;

public class ProductServiceUnavailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductServiceUnavailableException(Long id) {
		super("Product service unavailable. Cannot retrieve product with ID " + id);
	}

}
