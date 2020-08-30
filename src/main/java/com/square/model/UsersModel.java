package com.square.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UsersModel{

 	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;	


	@Column(name = "username")
	private String userName;	

	@Column(name = "password")
	private String password;

	@Column(name = "active")
	private boolean active;	

	@Column(name = "roles")
	private String roles;

	 @OneToMany
	 @JoinColumn(name = "user_id",insertable=false,  updatable=false, nullable = false, foreignKey = @ForeignKey(name = "Details_FK"))
	 List<BlogPostModel> postsList;	

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

     
	public List<BlogPostModel> getPostsList() {
		return postsList;
	}


	public void setPostsList(List<BlogPostModel> postsList) {
		this.postsList = postsList;
	}


	public UsersModel() {
	}

	
	
}