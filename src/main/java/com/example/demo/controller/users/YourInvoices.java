package com.example.demo.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Customers;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.repository.InvoicesRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class YourInvoices {

	@Autowired
	InvoicesRepository invoicesRepository;
	
	@Autowired
	CustomersRepository customersRepository;
	
	@GetMapping("/customer/yourinvoices")
	public ModelAndView yourInvoices(Model model, HttpSession session) {
		Customers customer = customersRepository.findByEmailId(session.getAttribute("email_id").toString());
		model.addAttribute("invoices", invoicesRepository.findInvoicesByCustomerId(customer.getCustomerId()));
		return new ModelAndView("users/yourinvoices");
	}
	
}
