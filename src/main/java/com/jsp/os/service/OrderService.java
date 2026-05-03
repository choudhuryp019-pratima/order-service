package com.jsp.os.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jsp.os.dto.OrderRequest;
import com.jsp.os.exception.OrderNotFoundException;
import com.jsp.os.model.Order;
import com.jsp.os.repository.OrderRepository;

import java.time.LocalDateTime;

	@Service
	public class OrderService {

	    @Autowired
	    private OrderRepository orderRepository;
	    
	    @Autowired 
	    private RestTemplate restTemplate;

	    public Order createOrder(OrderRequest request) {
	    	
	    	 // 🔹 Step 1: Check stock from Inventory Service
	        String checkUrl = "http://localhost:8086/inventory/check?productName="
	                + request.getProductName()
	                + "&quantity=" + request.getQuantity();

	        Boolean isAvailable = restTemplate.getForObject(checkUrl, Boolean.class);

	        if (Boolean.FALSE.equals(isAvailable)) {
	            throw new RuntimeException("Product out of stock");
	        }

	        // 🔹 Step 2: Reduce stock in Inventory Service
	        String reduceUrl = "http://localhost:8086/inventory/reduce?productName="
	                + request.getProductName()
	                + "&quantity=" + request.getQuantity();

	        restTemplate.postForObject(reduceUrl, null, String.class);

	        // 🔹 Step 3: Create Order
	        Order order = Order.builder()
	                .productName(request.getProductName())
	                .quantity(request.getQuantity())
	                .price(request.getPrice())
	                .status("CREATED")
	                .createdAt(LocalDateTime.now())
	                .build();

	        return orderRepository.save(order);
	    }

	    public Order getOrderById(Long id) {
	        return orderRepository.findById(id)
	                .orElseThrow(() ->
	                        new OrderNotFoundException("Order not found with id " + id));
	    }
	    }
	

	

