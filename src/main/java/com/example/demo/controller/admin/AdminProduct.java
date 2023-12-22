package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.CustomerOrders;
import com.example.demo.service.CustomerOrdersService;
import com.example.demo.service.ProductsService;

@RestController
public class AdminProduct {

	@Autowired
	ProductsService productsService;
	
	@Autowired
	CustomerOrdersService customerOrdersService;
	
	@GetMapping("/admin/adminproduct/{productCode}")
	public ModelAndView adminProduct(Model model, @PathVariable("productCode") Long orderId) {
		try {
			CustomerOrders orders = (CustomerOrders) customerOrdersService.getCustomerOrderById(orderId);
			model.addAttribute("product", productsService.getProductById(orders.getProducts().getProductCode()));
			model.addAttribute("orderId", orders.getOrderId());
			return new ModelAndView("admin/productview");
		}
		catch(Exception e) {
			return new ModelAndView("redirect:/admin/adminproducts");
		}
		
	}
	
	@GetMapping("/admin/dispatchproduct/{orderId}")
	public ModelAndView dispatchProduct(Model model, @PathVariable("orderId") long orderId) {
		try {
			CustomerOrders order = (CustomerOrders) customerOrdersService.getCustomerOrderById(orderId);
			if(!order.getOrderStatus().equals("Cancelled"))
			{
				order.setOrderStatus("DISPATCHED");
				customerOrdersService.updateCustomerOrder(order);
			}

		}
		catch(Exception e) {
			
		}
		return new ModelAndView("redirect:/admin/orders");
		
	}
}