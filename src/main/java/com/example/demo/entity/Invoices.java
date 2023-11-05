package com.example.demo.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "invoices")
public class Invoices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoiceId")
    private Long invoiceId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private CustomerOrders customerOrders;

    @Column(name = "invoiceDate")
    private Date invoiceDate;

    @Column(name = "cardType")
    private String cardType;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "invoiceAmount")
    private float invoiceAmount;

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public CustomerOrders getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(CustomerOrders customerOrders) {
		this.customerOrders = customerOrders;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
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

	public float getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(float invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	@Override
	public String toString() {
		return "Invoices [invoiceId=" + invoiceId + ", customerOrders=" + customerOrders + ", invoiceDate="
				+ invoiceDate + ", cardType=" + cardType + ", cardNumber=" + cardNumber + ", invoiceAmount="
				+ invoiceAmount + "]";
	}

	public Invoices() {
	}

    
    
    
}
