package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Products;

@Service
public interface ProductsService {
	boolean productIsExist(int productCode);
	void addProduct(Products product);
	List<Products> getProducts();
	void deleteProduct(int productCode);
	Object getProductById(int productCode);
	void updateProduct(Products product);
	List<Products> getProductsBySearch(String query);
}
