package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.InvoicesService;

@RestController
public class Invoices {

	@Autowired
	InvoicesService invoicesService;
	
	@GetMapping("/admin/invoices")
	public ModelAndView getInvoices(Model model) {
		model.addAttribute("invoices", invoicesService.getInvoices());
		return new ModelAndView("admin/invoices");
	}
	
}
