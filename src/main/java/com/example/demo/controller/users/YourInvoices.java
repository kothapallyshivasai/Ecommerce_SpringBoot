package com.example.demo.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Customers;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.repository.InvoicesRepository;

@RestController
public class YourInvoices {

	@Autowired
	InvoicesRepository invoicesRepository;
	
	@Autowired
	CustomersRepository customersRepository;
	
	@GetMapping("/customer/yourinvoices")
	public ModelAndView yourInvoices(Model model) {
		Customers customer = customersRepository.findByEmailId("shivasaikothapally@gmail.com");
		model.addAttribute("invoices", invoicesRepository.findInvoicesByCustomerId(customer.getCustomerId()));
		return new ModelAndView("users/yourinvoices");
	}
	
}
