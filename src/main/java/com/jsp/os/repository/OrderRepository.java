package com.jsp.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.os.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
