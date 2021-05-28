package com.example.foodfamily.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodfamily.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username);
    
    User findByEmail(String email);

    User findByActivationCode(String code);
}
