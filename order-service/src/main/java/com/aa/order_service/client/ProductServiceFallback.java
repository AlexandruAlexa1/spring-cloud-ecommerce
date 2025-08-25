package com.aa.order_service.client;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.aa.order_service.dto.ProductDTO;
import com.aa.order_service.exception.ProductServiceUnavailableException;

@Component
public class ProductServiceFallback implements ProductServiceClient {

	@Override
	public Optional<ProductDTO> getProductById(Long id) {
		throw new ProductServiceUnavailableException(id);
	}

}
