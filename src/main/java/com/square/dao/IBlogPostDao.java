package com.square.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.square.model.BlogPostModel;

@Repository
public interface IBlogPostDao extends JpaRepository<BlogPostModel, Integer> {

	List<BlogPostModel> findByIsApprovedTrueOrderByPostDateDesc();
	List<BlogPostModel> findByIsApprovedFalseOrderByPostDateDesc();
	
	List<BlogPostModel> findByUserName(String userName);
	
}
