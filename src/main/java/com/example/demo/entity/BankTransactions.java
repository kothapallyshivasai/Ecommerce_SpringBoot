package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bankTransactions")
public class BankTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionId")
    private Long transactionId;

    @Column(name = "toAccount")
    private Long toAccount;

    @Column(name = "fromAccount")
    private Long fromAccount;

    @Column(name = "amount")
    private float amount;

    @Column(name = "transactionDate", nullable = false)
    private Date transactionDate;

	public BankTransactions() {
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getToAccount() {
		return toAccount;
	}

	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}

	public Long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "BankTransactions [transactionId=" + transactionId + ", toAccount=" + toAccount + ", fromAccount="
				+ fromAccount + ", amount=" + amount + ", transactionDate=" + transactionDate + "]";
	}

    
    
}
