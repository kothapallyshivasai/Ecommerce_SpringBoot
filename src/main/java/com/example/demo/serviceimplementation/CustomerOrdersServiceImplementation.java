package com.example.demo.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerOrders;
import com.example.demo.repository.CustomerOrdersRepository;
import com.example.demo.service.CustomerOrdersService;

@Service
public class CustomerOrdersServiceImplementation implements CustomerOrdersService{
	@Autowired
	CustomerOrdersRepository customerOrdersRepository;

	@Override
	public boolean customerOrderIsExist(long orderId) {
		Optional<CustomerOrders> customerOrder = customerOrdersRepository.findById(orderId);
		if(customerOrder.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public void addCustomerOrder(CustomerOrders customerOrders) {
		if(!customerOrderIsExist(customerOrders.getOrderId())) {
			customerOrdersRepository.save(customerOrders);
		}
	}

	@Override
	public List<CustomerOrders> getCustomerOrders() {
		return customerOrdersRepository.findAll();
	}

	@Override
	public void deleteCustomerOrder(long orderId) {
		if(customerOrderIsExist(orderId)) {
			customerOrdersRepository.deleteById(orderId);
		}
	}

	@Override
	public Object getCustomerOrderById(long orderId) {
		if(customerOrderIsExist(orderId)) {
			return customerOrdersRepository.findById(orderId).get();
		}
		else {
			return new Exception("Order Doesn't Exist");
		}
	}

	@Override
	public void updateCustomerOrder(CustomerOrders customerOrders) {
		if(customerOrderIsExist(customerOrders.getOrderId())) {
			customerOrdersRepository.save(customerOrders);
		}
	}
}
