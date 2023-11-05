package com.example.demo.controller.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminHome {

	@GetMapping("/admin/adminhome")
	public ModelAndView adminHome(Model model) {
		return new ModelAndView("/admin/adminhome");
	}
	
}
