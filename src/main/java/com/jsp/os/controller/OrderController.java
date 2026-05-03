package com.jsp.os.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.os.dto.OrderRequest;
import com.jsp.os.model.Order;
import com.jsp.os.service.OrderService;

@RestController
@RequestMapping ("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public Order createOrder(@RequestBody OrderRequest request) {
	    return orderService.createOrder(request);
	}

	@GetMapping("/{id}")
	public Order getOrder(@PathVariable Long id) {
	    return orderService.getOrderById(id);
	}

}
