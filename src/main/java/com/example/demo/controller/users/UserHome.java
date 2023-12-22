package com.example.demo.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.ProductsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserHome {

	@Autowired
	ProductsService productsService;
	
	@GetMapping("/customer/userhome")
	public ModelAndView userHome(Model model) {
		model.addAttribute("products", productsService.getProducts());
		return new ModelAndView("users/userhome");
	}
	
	@GetMapping("/customer/logout")
	public ModelAndView customerLogout(HttpServletRequest request) {
		
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ModelAndView("redirect:/home");
    }
	
	
}
