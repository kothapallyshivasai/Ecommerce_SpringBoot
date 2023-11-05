package com.example.demo.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BankAccounts;
import com.example.demo.repository.BankAccountsRepository;
import com.example.demo.service.BankAccountsService;

@Service
public class BankAccountsServiceImplementation implements BankAccountsService{
	
	@Autowired
	BankAccountsRepository bankAccountsRepository;

	@Override
	public boolean bankAccountIsExist(Long accountNumber) {
		
		Optional<BankAccounts> bankAccount = bankAccountsRepository.findById(accountNumber);
		if (bankAccount.isPresent())
			return true;
		return false;
	}

	@Override
	public void addBankAccount(BankAccounts bankAccount) {
		
		boolean flag = bankAccountIsExist(bankAccount.getAccountNumber());
		if (!flag) {
			bankAccountsRepository.save(bankAccount);;
		}
	}

	@Override
	public List<BankAccounts> getBankAccounts() {
		return new ArrayList<>(bankAccountsRepository.findAll());
	}

	@Override
	public void deleteBankAccount(Long accountNumber) {
		
		boolean flag = bankAccountIsExist(accountNumber);
		if (flag) {
			bankAccountsRepository.deleteById(accountNumber);
		}
	}

	@Override
	public Object getBankAccountById(Long accountNumber) {
		
		Optional<BankAccounts> account = bankAccountsRepository.findById(accountNumber);
		if(account.isPresent()) {
			return account.get();
		}
		else {
			return new Exception("Account Doesn't Exist");
		}
	}

	@Override
	public void updateBankAccount(BankAccounts bankAccount) {
		boolean flag = bankAccountIsExist(bankAccount.getAccountNumber());
		if (flag) {
			bankAccountsRepository.save(bankAccount);
		}
	}
}
