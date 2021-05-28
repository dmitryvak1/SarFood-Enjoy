package com.example.foodfamily.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.foodfamily.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("select o from Order o where o.id = :idOrder")
	Order findOne(@Param("idOrder") Long idOrder);
	
	Page<Order> findByIdUser(Long idUser, Pageable pageable);
	
	Page<Order> findAll(Pageable pageable);
}	