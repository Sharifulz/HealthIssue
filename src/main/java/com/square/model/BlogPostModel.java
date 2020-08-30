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
@Table(name = "blog_post")
public class BlogPostModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;	


	@Column(name = "username")
	private String userName;	
	
	@Column(name = "description")
	private String description;
	
	//-------- This id is mapped with id in users table
	@Column(name = "user_id")
	private int userId;

	@OneToMany
	@JoinColumn(name = "post_id",insertable=false,  updatable=false, nullable = false, foreignKey = @ForeignKey(name = "Details_FK"))
	List<CommentsModel> commentsList;	
	
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<CommentsModel> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List<CommentsModel> commentsList) {
		this.commentsList = commentsList;
	}

	public BlogPostModel() {
	}
	
	
}
