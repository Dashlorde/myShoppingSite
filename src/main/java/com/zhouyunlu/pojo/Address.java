package com.zhouyunlu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="addressTable")
public class Address {

	@Id
	@Column(name="id", unique=true, nullable=false)
	private long id;
	
	//check phone string is number and has 10 digits
	@Pattern(regexp="[\\d]{10}", message="invalid phone number")
	@Column(name="phone")
	private String phone;
	
	@NotNull(message="address should not be null")
	@Size(min=1, message="please insert at least 1 character")
	@Column(name="address")
	private String address;
	
	@OneToOne(fetch=FetchType.LAZY)
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

