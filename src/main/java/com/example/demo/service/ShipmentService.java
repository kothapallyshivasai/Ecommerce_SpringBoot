package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Shipment;

@Service
public interface ShipmentService {
	boolean shipmentIsExist(int shipmentId);
	void addShipment(Shipment shipment);
	List<Shipment> getShipments();
	void deleteShipment(int shipmentId);
	Object getShipmentById(int shipmentId);
	void updateShipment(Shipment shipment);
}
