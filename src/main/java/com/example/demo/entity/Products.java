package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @Column(name = "productCode")
    private int productCode;

    @Column(name = "productName")
    private String productName;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private float cost;

    @Column(name = "discount")
    private float discount;

    @Column(name = "productImage")
    private String productImage;

    public Products() {
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

	@Override
	public String toString() {
		return "Products [productCode=" + productCode + ", productName=" + productName + ", category=" + category
				+ ", description=" + description + ", cost=" + cost + ", discount=" + discount + ", productImage="
				+ productImage + "]";
	}
    
}
