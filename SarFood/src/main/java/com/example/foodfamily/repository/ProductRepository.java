package com.example.foodfamily.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.foodfamily.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("select p from Product p where p.id = :idProduct")
	Product findOne(@Param("idProduct") Long idProduct);
	
	Page<Product> findAllByLocale(String locale, Pageable pageable);
	
	@Query("select p from Product p where p.type.id = :id and p.locale = :locale")
	Page<Product> findAllByCategoryIdAndLocale(@Param("id") Long id, @Param("locale") String locale, Pageable pageable);
}
