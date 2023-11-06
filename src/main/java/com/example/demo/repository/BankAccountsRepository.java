package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BankAccounts;

public interface BankAccountsRepository extends JpaRepository<BankAccounts, Long>{

	List<BankAccounts> findByEmailId(String emailId);
	
	BankAccounts findByCardNumber(String cardNumber);
	
}
