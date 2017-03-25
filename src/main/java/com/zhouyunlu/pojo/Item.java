package com.zhouyunlu.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Order_item")
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@ManyToOne(fetch=FetchType.EAGER)
	//@JoinColumn(name="orderToProduct", referencedColumnName="order_id")
	@Column(name="Order_order_id")
	@Id
	private long orderId;
	
	@Column(name="item")
	@Id
	private long productId;
	
	@Column(name="quantity")
	private int quantity;
	
	public Item(){
		
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
