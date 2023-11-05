package com.example.demo.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customers;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.service.CustomersService;

@Service
public class CustomersServiceImplementation implements CustomersService{

	@Autowired
	CustomersRepository customersRepository;

	public boolean validateLogin(String emailId, String password) {
		Customers customer = customersRepository.findByEmailId(emailId);
	    if (customer != null) {
	        if (password.equals(customer.getPassword())) {
	            return true;
	        }
	    }
		return false;
	}
	
	@Override
	public boolean customerIsExist(long customerId) {
		Optional<Customers> customer = customersRepository.findById(customerId);
		if(customer.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public void addCustomer(Customers customer) {
		customersRepository.save(customer);
	}

	@Override
	public List<Customers> getCustomers() {
		return customersRepository.findAll();
	}

	@Override
	public void deleteCustomer(long customerId) {
		if(customerIsExist(customerId)) {
			customersRepository.deleteById(customerId);
		}
		
	}

	@Override
	public Object getCustomerById(long customerId) {
		if(customerIsExist(customerId)) {
			return customersRepository.findById(customerId).get();
		}
		else {
			return new Exception("Customer Not Found");
		}
	}

	@Override
	public void updateCustomer(Customers customer) {
		if(!customerIsExist(customer.getCustomerId())) {
			customersRepository.save(customer);
		}
	}
	
	
}
