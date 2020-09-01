package com.square.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.square.model.BlogPostModel;

@Repository
@Transactional
public interface IBlogPostDao extends JpaRepository<BlogPostModel, Integer> {

	List<BlogPostModel> findByIsApprovedTrueOrderByPostDateDesc();
	List<BlogPostModel> findByIsApprovedFalseOrderByPostDateDesc();
	
	List<BlogPostModel> findByUserName(String userName);
	BlogPostModel findById(int id);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE BlogPostModel SET isApproved =:isApproved WHERE id =:id")
    int approveBlog(@Param("isApproved") boolean isApproved, @Param("id") int id);
}
