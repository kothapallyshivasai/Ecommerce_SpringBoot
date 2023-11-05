package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BankTransactions;

@Service
public interface BankTransactionsService {
	boolean bankTransactionIsExist(Long transactionId);
	void addBankTransaction(BankTransactions bankTransaction);
	List<BankTransactions> getBankTransactions();
	void deleteBankTransaction(Long transactionId);
	Object getBankTransactionById(Long transactionId);
	void updateBankTransaction(BankTransactions bankTransaction);
}
