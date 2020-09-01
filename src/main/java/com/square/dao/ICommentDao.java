package com.square.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.square.model.CommentsModel;

@Repository
@Transactional
public interface ICommentDao extends JpaRepository<CommentsModel, Integer> {

	@Query("DELETE FROM CommentsModel WHERE postId=:postId ")
	int deleteCorrespondingCommentsByPostId(@Param("postId") int postId);
	
}
