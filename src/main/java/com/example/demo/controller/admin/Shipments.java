package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.ShipmentService;

@RestController
public class Shipments {

	@Autowired
	ShipmentService shipmentService;
	
	@GetMapping("/admin/shipments")
	public ModelAndView getShipments(Model model) {
		model.addAttribute("shipments", shipmentService.getShipments());
		return new ModelAndView("admin/shipments");
	}
	
}
