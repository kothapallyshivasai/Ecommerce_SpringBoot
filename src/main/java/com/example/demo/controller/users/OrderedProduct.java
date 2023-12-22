package com.example.demo.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.CustomerOrders;
import com.example.demo.entity.Products;
import com.example.demo.repository.CustomerOrdersRepository;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.service.ProductsService;

import jakarta.servlet.http.HttpSession;

@RestController
public class OrderedProduct {
	
	@Autowired
	ProductsService productsService;
	
	@Autowired
	CustomerOrdersRepository customerOrdersRepository;
	
	@Autowired
	CustomersRepository customersRepository;
	
	@GetMapping("/customer/orderedProduct/{productCode}")
	public ModelAndView orderedProduct(Model model, @PathVariable("productCode") int productCode) {
		Products product = (Products) productsService.getProductById(productCode);
		model.addAttribute("product", product);
		return new ModelAndView("/users/orderedproduct");
	}
	
	@GetMapping("/customer/cancelorder/{productCode}")
	public ModelAndView cancelOrder(Model model, @PathVariable("productCode") int productCode, HttpSession session) {
		try {
			CustomerOrders customerOrder = customerOrdersRepository.findByCustomerAndProduct(customersRepository.findByEmailId(session.getAttribute("email_id").toString()), (Products) productsService.getProductById(productCode));
			//System.out.println("customer order= " + customerOrder);
			customerOrder.setOrderStatus("Cancelled");
			customerOrdersRepository.save(customerOrder);
		}
		catch(Exception e) {
			
		}
		return new ModelAndView("redirect:/customer/yourorders");
	}
	
}