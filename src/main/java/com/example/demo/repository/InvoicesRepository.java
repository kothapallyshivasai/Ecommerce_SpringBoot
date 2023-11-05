package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Invoices;

public interface InvoicesRepository extends JpaRepository<Invoices, Long>{

	 @Query("SELECT i FROM Invoices i JOIN i.customerOrders o WHERE o.customer.customerId = :customerId")
	 List<Invoices> findInvoicesByCustomerId(@Param("customerId") Long customerId);
	
}
