package com.zhouyunlu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="addressTable")
public class Address {

	@Id
	@Column(name="id", unique=true, nullable=false)
	private long id;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="address")
	private String address;
	
	@OneToOne(fetch=FetchType.LAZY)
	//@PrimaryKeyJoinColumn(name="id")
	@ForeignKey(name="user_id")
	private User user;
	
	
	public Address(){
		
	}
	
	public Address(String address){
		this.address=address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}	

