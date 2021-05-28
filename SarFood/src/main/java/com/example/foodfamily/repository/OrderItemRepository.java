package com.example.foodfamily.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.foodfamily.entity.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

	List<OrderItem> findByIdOrder(Long idOrder);
	
}
