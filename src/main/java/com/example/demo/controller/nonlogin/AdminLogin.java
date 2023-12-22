package com.example.demo.controller.nonlogin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Administrator;
import com.example.demo.service.AdministratorService;

@RestController
public class AdminLogin {

	@Autowired
	AdministratorService administratorService;
	
	@GetMapping("/adminlogin")
	public ModelAndView adminLogin(Model model) {
		return new ModelAndView("/nonlogin/adminlogin").addObject("admin", new Administrator());
	}
	
	@PostMapping("/validateAdminLogin")
	public ModelAndView validateAdminLogin(Model model, @ModelAttribute("admin") Administrator admin, RedirectAttributes redirectAttributes) {
		if(administratorService.validateAdministrator(admin)) {
			Optional<Administrator> administrator = administratorService.getUserById(admin.getUserName());
			Administrator admin2 = administrator.get();
			if(admin2.getRole().equals("ROLE_ADMIN")) {
				return new ModelAndView("redirect:/admin/adminhome");
			}
			return new ModelAndView("redirect:home");
		}
    	redirectAttributes.addFlashAttribute("invalid", true);
		return new ModelAndView("redirect:/adminlogin");
	}
	
}
