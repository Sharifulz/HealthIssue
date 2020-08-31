package com.square.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.square.model.UsersModel;

@Repository
public interface IUsersDao extends CrudRepository<UsersModel, Integer> {
	
	Optional<UsersModel> findByUserName(String userName);
	List<UsersModel> findByRolesAndActiveFalseOrderBySignupDate(String roles);
}
