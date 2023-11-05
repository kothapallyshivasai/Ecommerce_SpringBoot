package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BankAccounts;

@Service
public interface BankAccountsService {
	boolean bankAccountIsExist(Long accountNumber);
	void addBankAccount(BankAccounts bankAccount);
	List<BankAccounts> getBankAccounts();
	void deleteBankAccount(Long accountNumber);
	Object getBankAccountById(Long accountNumber);
	void updateBankAccount(BankAccounts bankAccount);
}
