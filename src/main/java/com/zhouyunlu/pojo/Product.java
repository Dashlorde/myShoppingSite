package com.zhouyunlu.pojo;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="productTable")
public class Product {

	@Id
	@GeneratedValue()
	@Column(name="productID")
	public long productID;
	
	@NotNull(message="price should not be none")
	@Min(value=1, message="price should at least be 1")
	@Column(name="productPrice")
	float productPrice;
	
	@NotNull(message="product name should not be none")
	@Size(min=1, message="please insert at least 1 character")
	@Column(name="productName")
	String productName;
	
	@NotNull(message="description should not be none")
	@Size(min=1, message="please insert at least 1 character")
	@Column(name="description")
	String description;
	
	@NotNull(message="category should not be none")
	@Size(min=1, message="please insert at least 1 character")
	@Column(name="category")
	String category;
	
	@Column(name="imageName")
	String imageName;
	
	
	@Column(name="username")
	 String username;
	
	@NotNull(message="stock should not be none")
	@Min(value=1, message="stock should at least be 1")
	@Column(name="stock")
	int stock;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="product",cascade=CascadeType.ALL)
	private Set<Comment> comment;
	
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}


	
  
}
