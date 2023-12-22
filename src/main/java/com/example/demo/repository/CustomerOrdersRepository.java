package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.CustomerOrders;
import com.example.demo.entity.Customers;
import com.example.demo.entity.Products;

public interface CustomerOrdersRepository extends JpaRepository<CustomerOrders, Long>{

	List<CustomerOrders> findByCustomerCustomerId(Long customerId);
	
	@Query("SELECT co FROM CustomerOrders co WHERE co.customer = :customer ORDER BY co.dateTime DESC")
	CustomerOrders findMostRecentOrderByCustomer(@Param("customer") Customers customer);
	
	@Query("SELECT co FROM CustomerOrders co WHERE co.customer = :customer AND co.products = :products")
	 CustomerOrders findByCustomerAndProduct(@Param("customer") Customers customer, @Param("products") Products products);

}
