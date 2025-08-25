package com.aa.order_service.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.aa.order_service.client.ProductServiceClient;
import com.aa.order_service.dto.OrderRequest;
import com.aa.order_service.dto.OrderResponse;
import com.aa.order_service.dto.ProductDTO;
import com.aa.order_service.entity.Order;
import com.aa.order_service.event.OrderPlacedEvent;
import com.aa.order_service.exception.InsufficientStockException;
import com.aa.order_service.exception.ProductNotFoundException;
import com.aa.order_service.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final ProductServiceClient client;
    private final StreamBridge streamBridge;

    public OrderService(OrderRepository repo, ProductServiceClient client, StreamBridge streamBridge) {
        this.repo = repo;
        this.client = client;
        this.streamBridge = streamBridge;
    }

    
    public OrderResponse placeOrder(OrderRequest request) {
    	ProductDTO product = client.getProductById(request.productId())
    			.orElseThrow(() ->  new ProductNotFoundException(request.productId()));

        if (product.stock() < request.quantity()) {
            throw new InsufficientStockException(request.productId());
        }

        BigDecimal total = BigDecimal.valueOf(product.price()).multiply(BigDecimal.valueOf(request.quantity()));

        Order savedOrder = repo.save(new Order(
            product.name(), 
            request.quantity(), 
            total
        ));
        
        OrderPlacedEvent event = new OrderPlacedEvent(
                savedOrder.getId(),
                savedOrder.getProductName(),
                savedOrder.getQuantity(),
                savedOrder.getTotalPrice()
        );
        
        streamBridge.send("order-out-0", event);

        return new OrderResponse(
            savedOrder.getId(), 
            savedOrder.getProductName(), 
            savedOrder.getQuantity(), 
            savedOrder.getTotalPrice()
        );
    }

    public List<OrderResponse> getAll() {
        return repo.findAll().stream()
            .map(o -> new OrderResponse(o.getId(), o.getProductName(), o.getQuantity(), o.getTotalPrice()))
            .toList();
    }
}

