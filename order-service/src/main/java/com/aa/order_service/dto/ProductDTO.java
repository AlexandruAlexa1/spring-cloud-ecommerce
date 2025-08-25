package com.aa.order_service.dto;

public record ProductDTO(
	    Long id,
	    String name,
	    Double price,
	    Integer stock
	) {}
