package com.example.demo.entity;

import java.util.List;

public class Buynow {

	private Customers customer;
	private CustomerOrders customerOrder;
	private Shipment shipment;
	private Invoices invoice;
	private Products product;
	private List<BankAccounts> accounts;
	
	
	public List<BankAccounts> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<BankAccounts> accounts) {
		this.accounts = accounts;
	}

	public Buynow() {
		
	}
	
	public Customers getCustomer() {
		return customer;
	}
	public void setCustomer(Customers customer) {
		this.customer = customer;
	}
	public CustomerOrders getCustomerOrder() {
		return customerOrder;
	}
	public void setCustomerOrder(CustomerOrders customerOrder) {
		this.customerOrder = customerOrder;
	}
	public Shipment getShipment() {
		return shipment;
	}
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
	public Invoices getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoices invoice) {
		this.invoice = invoice;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
}
