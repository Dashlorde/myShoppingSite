package com.zhouyunlu.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name="usertable")

public class User{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@NotNull(message="username should not be none")
	@Size(min=1, message="please insert at least 1 character")
	@Column(name="name")
    private String name;
	
	@NotNull(message="password should not be none")
	@Size(min=1, message="please insert at least 1 character")
	@Column(name="password")
    private String password;
	
	@NotNull(message="first name should not be none")
	@Size(min=1, message="please insert at least 1 character")
	@Column(name = "firstName")
	private String firstName;
	
	@NotNull(message="last name should not be none")
	@Size(min=1, message="please insert at least 1 character")
	@Column(name ="lastName")
	private String lastName;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="user", cascade=CascadeType.ALL)
    private Email email;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="user", cascade=CascadeType.ALL)
	private Address address;
   
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user", cascade=CascadeType.ALL)
	private Set<Comment> commentSet;
	
	@Column(name="paypal_username")
	String paypalUsername;
	
	@Column(name="paypal_password")
	String paypalPassword;
	
	@Column(name="signature")
	String signature;

	public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

	public Email getEmail() {
		return email;
		
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Comment> getCommentSet() {
		return commentSet;
	}

	public void setCommentSet(Set<Comment> commentSet) {
		this.commentSet = commentSet;
	}

	public String getPaypalUsername() {
		return paypalUsername;
	}

	public void setPaypalUsername(String paypalUsername) {
		this.paypalUsername = paypalUsername;
	}

	public String getPaypalPassword() {
		return paypalPassword;
	}

	public void setPaypalPassword(String paypalPassword) {
		this.paypalPassword = paypalPassword;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	
	

}
