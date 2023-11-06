package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Customers;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	List<Cart> findByCustomersCustomerId(Long customerId);
	Cart findFirstByCustomersAndProductCode(Customers customers, int productCode);
}
