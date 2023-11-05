package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Long>{
	Customers findByEmailId(String emailId);
}
