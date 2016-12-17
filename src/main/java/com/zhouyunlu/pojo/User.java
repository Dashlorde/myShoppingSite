package com.zhouyunlu.pojo;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="usertable")

public class User{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="name")
    private String name;
	
	@Column(name="password")
    private String password;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name ="lastName")
	private String lastName;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="user", cascade=CascadeType.ALL)
    private Email email;
	
	/*@OneToOne(fetch=FetchType.LAZY, mappedBy="user", cascade=CascadeType.ALL)
	private Address address;
	*/
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id")
	private Address address;
   

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

	/*public Address getAdddress() {
		return adddress;
	}

	public void setAdddress(Address adddress) {
		this.adddress = adddress;
	}
	*/
	
	

}
