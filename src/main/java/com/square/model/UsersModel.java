package com.square.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UsersModel{

 	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;	


	@Column(name = "username", unique = true)
	private String userName;	

	@Column(name = "full_name")
	private String fullName;	

	@Column(name = "signup_date")
	private Date signupDate;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "active")
	private boolean active;	

	@Column(name = "roles")
	private String roles;

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}
	 
	


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public Date getSignupDate() {
		return signupDate;
	}


	public void setSignupDate(Date signupDate) {
		this.signupDate = signupDate;
	}


	public UsersModel() {
	}

	
	
}