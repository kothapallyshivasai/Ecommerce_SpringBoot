package com.example.demo.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Customers;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.repository.ShipmentRepository;

@RestController
public class YourShipments {

	@Autowired
	ShipmentRepository shipmentRepository;
	
	@Autowired
	CustomersRepository customerRepository;
	
	@GetMapping("/customer/yourshipments")
	public ModelAndView yourShipments(Model model) {
		Customers customer = customerRepository.findByEmailId("shivasaikothapally@gmail.com");
		model.addAttribute("shipments", shipmentRepository.findShipmentsByCustomerId(customer.getCustomerId()));
		return new ModelAndView("users/yourshipments");
	}
	
}
