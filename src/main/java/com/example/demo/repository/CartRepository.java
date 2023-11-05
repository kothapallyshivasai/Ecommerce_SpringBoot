package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	List<Cart> findByCustomersCustomerId(Long customerId);
}
