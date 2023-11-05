package com.example.demo.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;

@Service
public class CartServiceImplementation implements CartService{

	@Autowired
	CartRepository cartRepository;

	@Override
	public boolean cartIsExist(int cartId) {
		Optional<Cart> cart = cartRepository.findById(cartId);
		if (cart.isPresent()) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<Cart> getCartItemsByCustomerId(Long customerId) {
        return cartRepository.findByCustomersCustomerId(customerId);
    }

	@Override
	public void addCart(Cart cart) {
		if(!cartIsExist(cart.getCartId())) {
			cartRepository.save(cart);
		}
	}

	@Override
	public List<Cart> getCarts() {
		return cartRepository.findAll();
	}

	@Override
	public void deleteCart(int cartId) {
		if(cartIsExist(cartId)) {
			cartRepository.deleteById(cartId);
		}
	}

	@Override
	public Object getCartById(int cartId) {
		if(cartIsExist(cartId)) {
			return cartRepository.findById(cartId).get();
		}
		else {
			return new Exception("Cart Doesn't Exist");
		}
	}

	@Override
	public void updateCart(Cart cart) {
		if(cartIsExist(cart.getCartId())) {
			cartRepository.save(cart);
		}
	}
	
}
