package com.example.demo.serviceimplementation;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Shipment;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.ShipmentService;

@Service
public class ShipmentServiceImplementation implements ShipmentService{
	@Autowired
	ShipmentRepository shipmentRepository;

	@Override
	public boolean shipmentIsExist(int shipmentId) {
		Optional<Shipment> shipment = shipmentRepository.findById(shipmentId);
		if(shipment.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public void addShipment(Shipment shipment) {
		if(!shipmentIsExist(shipment.getShipmentId())) {
			shipmentRepository.save(shipment);
		}
	}

	@Override
	public List<Shipment> getShipments() {
		return shipmentRepository.findAll();
	}

	@Override
	public void deleteShipment(int shipmentId) {
		if(shipmentIsExist(shipmentId)) {
			shipmentRepository.deleteById(shipmentId);
		}
	}

	@Override
	public Object getShipmentById(int shipmentId) {
		if(shipmentIsExist(shipmentId)) {
			return shipmentRepository.findById(shipmentId).get();
		}
		else {
			return new Exception();
		}
	}

	@Override
	public void updateShipment(Shipment shipment) {
		if(!shipmentIsExist(shipment.getShipmentId())) {
			shipmentRepository.save(shipment);
		}
	}
	
}
