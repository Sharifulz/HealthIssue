package com.square.service;

import java.util.Map;

import com.square.model.BlogPostModel;

public interface IBlogService {
	public Map<String, Object> changeApprovalStatus(Boolean activeStatus, int id); 
	public Map<String, Object> deleteBlog(int id);
	public Map<String, Object> saveBlog(BlogPostModel blog);
	public Map<String, Object> saveBlogByUser(BlogPostModel blog);
	
}
