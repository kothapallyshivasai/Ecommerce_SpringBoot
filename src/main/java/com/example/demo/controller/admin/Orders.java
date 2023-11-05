package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.CustomerOrdersService;

@RestController
public class Orders {
	
	@Autowired
	CustomerOrdersService customerOrdersService;
	
	@GetMapping("/admin/orders")
	public ModelAndView viewOrders(Model model) {
		model.addAttribute("orders", customerOrdersService.getCustomerOrders());
		return new ModelAndView("admin/orders");
	}
	
}
