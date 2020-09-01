package com.square.service;

import java.util.Map;

import com.square.model.UsersModel;

public interface IUserService {

	public Map<String, Object> getCurrentUserDetails(String userName);
	
	public Map<String, Object> saveUser(UsersModel user);
	
	public Map<String, Object> getAllDataForAdmin(); 
	
	public Map<String, Object> changeActivationStatus(Boolean activeStatus, int id); 
	
	public Map<String, Object> deleteUser(int id); 
	
	
}
