package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "customerOrders")
public class CustomerOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customers customer;

    @Override
	public String toString() {
		return "CustomerOrders [orderId=" + orderId + ", customer=" + customer + ", dateTime=" + dateTime
				+ ", products=" + products + ", cost=" + cost + ", discountAmount=" + discountAmount + ", billAmount="
				+ billAmount + ", orderStatus=" + orderStatus + "]";
	}

	@Column(name = "dateTime")
    private Date dateTime;

    @ManyToOne
    @JoinColumn(name = "productCode")
    private Products products;

    @Column(name = "cost")
    private float cost;

    @Column(name = "discountAmount")
    private float discountAmount;

    @Column(name = "billAmount")
    private float billAmount;

    @Column(name = "orderStatus")
    private String orderStatus;

    public CustomerOrders() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public float getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(float billAmount) {
        this.billAmount = billAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
