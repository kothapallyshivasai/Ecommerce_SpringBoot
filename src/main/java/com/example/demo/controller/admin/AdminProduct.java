package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.ProductsService;

@RestController
public class AdminProduct {

	@Autowired
	ProductsService productsService;
	
	@GetMapping("/admin/adminproduct/{productCode}")
	public ModelAndView adminProduct(Model model, @PathVariable("productCode") int productCode) {
		try {
			model.addAttribute("product", productsService.getProductById(productCode));
			return new ModelAndView("admin/productview");
		}
		catch(Exception e) {
			return new ModelAndView("redirect:/admin/adminproducts");
		}
		
	}
}
