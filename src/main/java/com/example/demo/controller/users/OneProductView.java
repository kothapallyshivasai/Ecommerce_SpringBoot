package com.example.demo.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.ProductsService;

@RestController
public class OneProductView {

	@Autowired
	ProductsService productsService;
	
	@GetMapping("/customer/userproduct/{productCode}")
	public ModelAndView getProduct(Model model, @PathVariable("productCode") int productCode) {
		model.addAttribute("product", productsService.getProductById(productCode));
		return new ModelAndView("users/userproductview");
	}
	
}
