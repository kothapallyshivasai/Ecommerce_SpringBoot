package com.example.demo.controller.nonlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Administrator;
import com.example.demo.repository.AdministratorRepository;
//import com.example.demo.entity.Customers;
import com.example.demo.service.AdministratorService;
//import com.example.demo.service.CustomersService;

@RestController
public class Login {

	@Autowired
	AdministratorService customersService;
	
	@Autowired
	AdministratorRepository administratorRepository;
	
	@GetMapping("/customerlogin")
	public ModelAndView loginPage(Model model) {
		return new ModelAndView("/nonlogin/login").addObject("customer", new Administrator());
	}
	
	@PostMapping("/validateCustomerLogin")
	public ModelAndView validateCustomerLogin(Model model, @ModelAttribute("customer") Administrator customer, RedirectAttributes redirectAttributes)
	{
		if (customersService.validateAdministrator(customer)) {
			Administrator customer2 = administratorRepository.findById(customer.getUserName()).get();
			if(customer2.getRole().equals("USER"))
				return new ModelAndView("redirect:/customer/userhome"); 
			return new ModelAndView("redirect:home"); 
        } 
		else {
            redirectAttributes.addFlashAttribute("invalid", true);
            return new ModelAndView("redirect:customerlogin");
        }
	}
}
