package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BankTransactions;

public interface BankTransactionsRepository extends JpaRepository<BankTransactions, Long>{

}
