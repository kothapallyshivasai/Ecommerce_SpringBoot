package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Products;
import com.example.demo.service.ProductsService;

@RestController
public class AdminSearch {

	@Autowired
	ProductsService productsService;
	
	@GetMapping("/admin/adminsearch")
	public ModelAndView adminSearch(@RequestParam(name = "search") String searchQuery, Model model) {
		
		List<Products> products = productsService.getProductsBySearch(searchQuery);
		model.addAttribute("products", products);
		return new ModelAndView("/admin/adminsearch");
	}
}
