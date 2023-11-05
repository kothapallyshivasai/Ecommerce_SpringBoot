package com.example.demo.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BankTransactions;
import com.example.demo.repository.BankTransactionsRepository;
import com.example.demo.service.BankTransactionsService;

@Service
public class BankTransactionsServiceImplementation implements BankTransactionsService{

	@Autowired
	BankTransactionsRepository bankTransactionsRepository;
	
	@Override
	public boolean bankTransactionIsExist(Long transactionId) {
		Optional<BankTransactions> transaction = bankTransactionsRepository.findById(transactionId);
		if(transaction.isPresent())
			return true;
		return false;
	}

	@Override
	public void addBankTransaction(BankTransactions bankTransaction) {
		if(!bankTransactionIsExist(bankTransaction.getTransactionId())) {
			bankTransactionsRepository.save(bankTransaction);
		}
	}

	@Override
	public List<BankTransactions> getBankTransactions() {
		return new ArrayList<>(bankTransactionsRepository.findAll());
	}

	@Override
	public void deleteBankTransaction(Long transactionId) {
		if(bankTransactionIsExist(transactionId)) {
			bankTransactionsRepository.deleteById(transactionId);
		}
	}

	@Override
	public Object getBankTransactionById(Long transactionId) {
		if(bankTransactionIsExist(transactionId)) {
			return bankTransactionsRepository.findById(transactionId).get();
		}
		else {
			return new Exception("Transaction Doesn't Exist");
		}
	}

	@Override
	public void updateBankTransaction(BankTransactions bankTransaction) {
		if(bankTransactionIsExist(bankTransaction.getTransactionId())) {
			bankTransactionsRepository.save(bankTransaction);
		}
	}

}
