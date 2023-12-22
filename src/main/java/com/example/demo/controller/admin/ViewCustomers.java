package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.CustomersService;

@RestController
public class ViewCustomers {

	@Autowired
	CustomersService customersService;
	
	@GetMapping("/admin/viewcustomers")
	public ModelAndView viewCustomers(Model model) {
		model.addAttribute("customers", customersService.getCustomers());
		return new ModelAndView("admin/view_customers");
	}
	
	@GetMapping("/admin/admindeletecustomer/{customerId}")
	public ModelAndView adminDeleteCustomer(Model model, @PathVariable("customerId") long customerId, RedirectAttributes redirectAttributes) {
		try {
			customersService.deleteCustomer(customerId);
		}
		catch(Exception e) {
	    	redirectAttributes.addFlashAttribute("valid", true);
		}
		
		return new ModelAndView("redirect:/admin/viewcustomers");	
	}
	
}
