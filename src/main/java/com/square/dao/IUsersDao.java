package com.square.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.square.model.UsersModel;

@Repository
public interface IUsersDao extends JpaRepository<UsersModel, Integer> {
	
	Optional<UsersModel> findByUserName(String userName);
	
}
