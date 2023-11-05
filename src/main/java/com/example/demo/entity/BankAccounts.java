package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bankAccounts")
public class BankAccounts {
	
	@Id
	@Column(name = "accountNumber")
	private long accountNumber;
	
	@Column(name = "accountDate")
	private Date accountDate;
	
	@Column(name = "customerName")
	private String customerName;
	
	@Column(name = "contactAddress")
	private String contactAddress;
	
	@Column(name = "mobileNumber")
	private long mobileNumber;
	
	@Column(name = "emailId", unique = true)
	private String emailId;
	
	@Column(name = "cardType")
	private String cardType;
	
	@Column(name = "cardNumber", unique = true)
	private String cardNumber;
	
	@Column(name = "expiryDate")
	private String expiryDate;
	
	@Column(name = "cvvNumber")
	private int cvvNumber;
	
	@Column(name = "balanceAmount")
	private float balanceAmount;

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getCvvNumber() {
		return cvvNumber;
	}

	public void setCvvNumber(int cvvNumber) {
		this.cvvNumber = cvvNumber;
	}

	public float getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(float balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	@Override
	public String toString() {
		return "BankAccounts [accountNumber=" + accountNumber + ", accountDate=" + accountDate + ", customerName="
				+ customerName + ", contactAddress=" + contactAddress + ", mobileNumber=" + mobileNumber + ", emailId="
				+ emailId + ", cardType=" + cardType + ", cardNumber=" + cardNumber + ", expiryDate=" + expiryDate
				+ ", cvvNumber=" + cvvNumber + ", balanceAmount=" + balanceAmount + "]";
	}

	public BankAccounts() {

	}
	
}
