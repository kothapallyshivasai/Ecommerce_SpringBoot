package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;

@Service
public interface CartService {
	boolean cartIsExist(int cartId);
	void addCart(Cart cart);
	List<Cart> getCarts();
	void deleteCart(int cartId);
	Object getCartById(int cartId);
	void updateCart(Cart cart);
	List<Cart> getCartItemsByCustomerId(Long customerId);
}
