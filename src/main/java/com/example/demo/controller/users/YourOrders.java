package com.example.demo.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Customers;
import com.example.demo.repository.CustomerOrdersRepository;
import com.example.demo.repository.CustomersRepository;

@RestController
public class YourOrders {

	@Autowired
	CustomerOrdersRepository customerOrdersRepository;
	
	@Autowired
	CustomersRepository customersRepository;
	
	@GetMapping("/customer/yourorders")
	public ModelAndView yourOrders(Model model) {
		Customers customer = customersRepository.findByEmailId("shivasaikothapally@gmail.com");
		model.addAttribute("orders", customerOrdersRepository.findByCustomerCustomerId(customer.getCustomerId()));
		return new ModelAndView("users/yourorders");
	}
	
}
