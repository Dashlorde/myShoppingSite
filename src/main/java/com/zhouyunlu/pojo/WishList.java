package com.zhouyunlu.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wishlist")
public class WishList implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	@Id
	private long userId;
	
	@Column(name="product_id")
	@Id
	private long productId;
	
	public WishList(){
		
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	
}
