package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipmentId")
    private int shipmentId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private CustomerOrders customerOrders;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customers customers;

    @Column(name = "deliveryAddress", length = 200)
    private String deliveryAddress;

    @Column(name = "zipCode", length = 10)
    private String zipCode;

    @Column(name = "mobileNumber", length = 12)
    private String mobileNumber;

	@Override
	public String toString() {
		return "Shipment [shipmentId=" + shipmentId + ", customerOrders=" + customerOrders + ", customers=" + customers
				+ ", deliveryAddress=" + deliveryAddress + ", zipCode=" + zipCode + ", mobileNumber=" + mobileNumber
				+ "]";
	}

	public int getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}

	public CustomerOrders getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(CustomerOrders customerOrders) {
		this.customerOrders = customerOrders;
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Shipment() {
	}

    
}
