package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerOrders;

public interface CustomerOrdersRepository extends JpaRepository<CustomerOrders, Long>{

	List<CustomerOrders> findByCustomerCustomerId(Long customerId);
	
}
