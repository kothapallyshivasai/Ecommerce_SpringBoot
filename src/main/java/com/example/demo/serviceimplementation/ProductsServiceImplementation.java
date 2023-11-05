package com.example.demo.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Products;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.service.ProductsService;

@Service
public class ProductsServiceImplementation implements ProductsService{
	@Autowired
	ProductsRepository productsRepository;

	@Override
	public boolean productIsExist(int productCode) {
		Optional<Products> product = productsRepository.findById(productCode);
		if(product.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public void addProduct(Products product) {
		if(!productIsExist(product.getProductCode())) {
			productsRepository.save(product);
		}
	}

	@Override
	public List<Products> getProducts() {
		return productsRepository.findAll();
	}

	@Override
	public void deleteProduct(int productCode) {
		if(productIsExist(productCode)) {
			productsRepository.deleteById(productCode);
		}
	}

	@Override
	public Object getProductById(int productCode) {
		if(productIsExist(productCode)) {
			return productsRepository.findById(productCode).get();
		}
		else {
			return new Exception("Product Doesn't Exist");
		}
	}

	@Override
	public void updateProduct(Products product) {
		if(productIsExist(product.getProductCode())) {
			productsRepository.save(product);
		}
	}

	@Override
	public List<Products> getProductsBySearch(String query) {
		return productsRepository.findByCategory(query);
	}
}
