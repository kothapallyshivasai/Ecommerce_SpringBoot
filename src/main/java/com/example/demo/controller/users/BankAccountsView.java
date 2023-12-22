package com.example.demo.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.BankAccounts;
import com.example.demo.repository.BankAccountsRepository;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.service.BankAccountsService;

import jakarta.servlet.http.HttpSession;

@RestController
public class BankAccountsView {

	@Autowired
	BankAccountsRepository bankAccountsRepository;
	
	@Autowired
	BankAccountsService bankAccountsService;
	
	@Autowired
	CustomersRepository customersRepository;
	
	@GetMapping("/customer/bankaccounts")
	public ModelAndView getBankAccounts(Model model,  HttpSession session) {
		
		model.addAttribute("accounts", bankAccountsRepository.findByEmailId(session.getAttribute("email_id").toString()));
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
	
	@GetMapping("/customer/addBalance/{accountNumber}")
	public ModelAndView addBalance(Model model, @PathVariable("accountNumber") Long accountNumber) {
		model.addAttribute("bankAccount", new BankAccounts());
		model.addAttribute("accountNumber", accountNumber);
		return new ModelAndView("/users/addbalance");
	}
	
	@PostMapping("/customer/addbalance/{accountNumber}")
	public ModelAndView addBalanceSave(@PathVariable("accountNumber") Long accountNumber, Model model, @ModelAttribute("bankAccount") BankAccounts bankAccount) {
		BankAccounts account = (BankAccounts) bankAccountsService.getBankAccountById(accountNumber);
		Float balance = account.getBalanceAmount();
		account.setBalanceAmount(balance + bankAccount.getBalanceAmount());
		bankAccountsService.updateBankAccount(account);
		return new ModelAndView("redirect:/customer/bankaccounts");
	}
	
}

