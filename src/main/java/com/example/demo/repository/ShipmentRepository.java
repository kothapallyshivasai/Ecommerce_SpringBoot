package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{

	 @Query("SELECT s FROM Shipment s WHERE s.customers.customerId = :customerId")
	 List<Shipment> findShipmentsByCustomerId(@Param("customerId") Long customerId);
}
