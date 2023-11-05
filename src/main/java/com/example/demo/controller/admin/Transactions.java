package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.BankTransactionsService;

@RestController
public class Transactions {
	
	@Autowired
	BankTransactionsService bankTransactionsService;
	
	@GetMapping("/admin/transactions")
	public ModelAndView getTransactions(Model model) {
		model.addAttribute("transactions", bankTransactionsService.getBankTransactions());
		return new ModelAndView("admin/transactions");
	}
	
}
