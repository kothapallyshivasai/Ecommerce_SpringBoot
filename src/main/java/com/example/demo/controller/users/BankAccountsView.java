package com.example.demo.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.BankAccounts;
import com.example.demo.entity.Customers;
import com.example.demo.repository.BankAccountsRepository;
import com.example.demo.repository.CustomersRepository;

@RestController
public class BankAccountsView {

	@Autowired
	BankAccountsRepository bankAccountsRepository;
	
	@Autowired
	CustomersRepository customersRepository;
	
	@GetMapping("/customer/bankaccounts")
	public ModelAndView getBankAccounts(Model model) {
		Customers customer = customersRepository.findByEmailId("shivasaikothapally@gmail.com");
		model.addAttribute("accounts", bankAccountsRepository.findByEmailId(customer.getEmailId()));
		return new ModelAndView("users/bankaccounts");
	}
	
	@GetMapping("/customer/addbankaccount")
	public ModelAndView addBankAccount(Model model) {
		model.addAttribute("account", new BankAccounts());
		return new ModelAndView("users/addbankaccount");
	}
	
	@PostMapping("/customer/validatenewbankaccount")
	public ModelAndView validateNewBankAccount(Model model, @ModelAttribute("account") BankAccounts account) {
		bankAccountsRepository.save(account);
		return new ModelAndView("redirect:/customer/bankaccounts");
	}
	
}
