package com.example.foodfamily.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.foodfamily.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>  {
	
	List<Category> findAll();
	
	@Query("select c from Category c where c.id = :idCategory")
	Category findOne(@Param("idCategory") Long id);
}
