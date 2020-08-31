package com.square.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.Entity;
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
	
	@Column(name = "post_date")
	private Date postDate;
	
	@Column(name = "is_approved", columnDefinition = "boolean default false")
	private boolean isApproved;
	
	@Column(name = "likes", columnDefinition = "integer default 0")
	private int likes;	
	
	@Column(name = "dislikes", columnDefinition = "integer  default 0")
	private int dislikes;	
	
	@Column(name = "comments", columnDefinition = "integer  default 0")
	private int comments;	
	
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


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	
	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
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
