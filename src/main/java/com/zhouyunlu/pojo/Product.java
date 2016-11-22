package com.zhouyunlu.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import javax.persistence.Table;


@Entity
@Table(name="productTable")
public class Product {

	@Id
	@GeneratedValue()
	@Column(name="productID")
	public long productID;
	
	@Column(name="productPrice")
	float productPrice;
	
	@Column(name="productName")
	String productName;
	
	@Column(name="description")
	String description;
	
	@Column(name="category")
	String category;
	
	@Column(name="imageName")
	String imageName;
	
	@Column(name="username")
	 String username;
		
	
	
	public Product(){
		
	}
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		 this.imageName = imageName;
	}

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
  
}
