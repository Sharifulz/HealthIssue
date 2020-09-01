package com.square.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.square.model.UsersModel;

@Repository
@Transactional
public interface IUsersDao extends CrudRepository<UsersModel, Integer> {
	
	Optional<UsersModel> findByUserName(String userName);
	UsersModel findById(int id);
	List<UsersModel> findByRolesAndActiveFalseOrderBySignupDate(String roles);
	List<UsersModel> findByRolesAndActiveTrueOrderBySignupDate(String roles);
	
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE UsersModel SET active =:active WHERE id =:id")
    int activateUser(@Param("active") boolean active, @Param("id") int id);
	
}
