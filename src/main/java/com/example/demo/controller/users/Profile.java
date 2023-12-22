package com.example.demo.controller.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Customers;
import com.example.demo.repository.CustomersRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class Profile {

	@Autowired
	CustomersRepository customersRepository;
	
	@GetMapping("/customer/profile")
	public ModelAndView getProfile(Model model, HttpSession session) {
		
		Customers customer = customersRepository.findByEmailId(session.getAttribute("email_id").toString());
		model.addAttribute("customer", customer);
		return new ModelAndView("users/profile");
	}
	
	@PostMapping("/customer/savechanges")
	public ModelAndView saveProfile(Model model, @ModelAttribute("customer") Customers customer) {
		try {
			customersRepository.save(customer);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("redirect:/customer/profile");
	}
	
}
