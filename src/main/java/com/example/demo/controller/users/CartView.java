package com.example.demo.controller.users;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Customers;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductsService;

import jakarta.servlet.http.HttpSession;

@RestController
public class CartView {

	@Autowired
	CartService cartService; 
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CustomersRepository customersRepository;
	
	@Autowired
	ProductsService productsService;
	
	@Autowired
	ProductsRepository productsRepository;
	
	@GetMapping("/customer/addtocart/{productCode}")
	public ModelAndView addToCart(Model model, @PathVariable("productCode") int productCode, HttpSession session) {
		 Customers customer = customersRepository.findByEmailId(session.getAttribute("email_id").toString());
	     List<Cart> existingCarts = cartRepository.findByCustomersCustomerId(customer.getCustomerId());
	     boolean flag = true;

	     for (Cart cart : existingCarts) {
	    	 if (cart.getProductCode() == productCode) {
	                flag = false;
	         }
	     }
	     
	     if(flag && productsService.productIsExist(productCode)) {
	    	 Cart cart = new Cart();
	    	 cart.setCustomerId(customer);
	    	 cart.setProductCode(productCode);
             Date currentDate = new Date(new java.util.Date().getTime());
             cart.setSelectedDate(currentDate);
             cartService.addCart(cart);
	     }
	     
	     return new ModelAndView("redirect:/customer/userhome");
	}
	
	@GetMapping("/customer/cart")
    public ModelAndView getCartItemsForCustomer(Model model, HttpSession session) {
		Customers customer = customersRepository.findByEmailId(session.getAttribute("email_id").toString());
		List<Cart> carts = cartRepository.findByCustomersCustomerId(customer.getCustomerId());
		model.addAttribute("carts", carts);
		List<Integer> productCodes = new ArrayList<>();
		for(Cart c: carts) {
			productCodes.add(c.getProductCode());
		}
		model.addAttribute("products", productsRepository.findByProductCodeIn(productCodes));
        return new ModelAndView("users/cartview");
    }
	
	@GetMapping("/customer/removecart/{productCode}")
	public ModelAndView removeCart(Model model, @PathVariable("productCode") int productCode, HttpSession session) {
		Customers customer = customersRepository.findByEmailId(session.getAttribute("email_id").toString());
		Cart cart = cartRepository.findFirstByCustomersAndProductCode(customer, productCode);
		cartRepository.delete(cart);
		return new ModelAndView("redirect:/customer/cart");
	}
	
}
