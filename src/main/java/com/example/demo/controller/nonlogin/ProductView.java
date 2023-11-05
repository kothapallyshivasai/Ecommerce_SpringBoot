package com.example.demo.controller.nonlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Products;
import com.example.demo.service.ProductsService;

@RestController
public class ProductView {
	
	@Autowired
	ProductsService productsService;
	
	@GetMapping("/product/{productCode}")
	public ModelAndView getProduct(Model model, @PathVariable("productCode") int productCode) {
		if(productsService.productIsExist(productCode)) {
			model.addAttribute("product", (Products) productsService.getProductById(productCode));
			return new ModelAndView("/nonlogin/productview");
		}
		return new ModelAndView("redirect:/home");
	}
}
