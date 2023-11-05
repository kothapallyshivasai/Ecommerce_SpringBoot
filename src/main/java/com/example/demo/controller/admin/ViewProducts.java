package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.ProductsService;

@RestController
public class ViewProducts {
	@Autowired
	ProductsService productsService;
	
	@GetMapping("/admin/adminproducts")
	public ModelAndView viewProducts(Model model) {
		
		model.addAttribute("products", productsService.getProducts());
		return new ModelAndView("admin/products");
	}
}
