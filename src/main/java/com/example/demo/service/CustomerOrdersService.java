package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerOrders;

@Service
public interface CustomerOrdersService {
	boolean customerOrderIsExist(long orderId);
	void addCustomerOrder(CustomerOrders customerOrders);
	List<CustomerOrders> getCustomerOrders();
	void deleteCustomerOrder(long orderId);
	Object getCustomerOrderById(long orderId);
	void updateCustomerOrder(CustomerOrders customerOrders);
}
