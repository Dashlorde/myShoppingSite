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
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.type.TextType;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="productTable")
public class Product implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue()
	@Column(name="productID")
	private long productID;
	
	@NotNull(message="price should not be none")
	@Range(min=0, message="price should at least be 0")
	@Valid
	@Column(name="productPrice")	
	private float productPrice;
	
	@NotNull(message="product name should not be none")
	@Size(min=1, message="please insert at least 1 character")
	@Column(name="productName")
	private String productName;
	
	@NotNull(message="description should not be none")
	@Size(min=1, message="please insert at least 1 character")
	@Column(name="description")
	private String description;
	
	@NotNull(message="category should not be none")
	@Size(min=1, message="please insert at least 1 character")
	@Column(name="category")
	private String category;
	
	@Column(name="imageName")
	private String imageName;
	
	
	@Column(name="username")
	private String username;
	
	@NotNull(message="stock should not be none")
	@Range(min=1, message="stock should at least be 1")
	@Column(name="stock")
	@Valid
	private int stock;
	
	
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

	@JsonIgnore
	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}


	
  
}
