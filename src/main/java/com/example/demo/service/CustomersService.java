package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Customers;

@Service
public interface CustomersService {
	boolean customerIsExist(long customerId);
	void addCustomer(Customers customer);
	List<Customers> getCustomers();
	void deleteCustomer(long customerId);
	Object getCustomerById(long customerId);
	void updateCustomer(Customers customer);
	boolean validateLogin(String emailId, String password);
}
