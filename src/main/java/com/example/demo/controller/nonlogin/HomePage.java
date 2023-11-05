package com.example.demo.controller.nonlogin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Products;
import com.example.demo.service.ProductsService;

@RestController
public class HomePage {

	@Autowired
	ProductsService productsService;
	
	@GetMapping("/home")
	public ModelAndView homeNonLogin(Model model) {
		
		List<Products> products = productsService.getProducts();
	    List<Products> first12Products = products.size() > 12 ? products.subList(0, 12) : products;
		model.addAttribute("products", first12Products);
		return new ModelAndView("/nonlogin/index");
	}
}