package com.square.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.square.dao.IBlogPostDao;
import com.square.dao.IUsersDao;
import com.square.model.BlogPostModel;
import com.square.model.UsersModel;
import com.square.service.common.ICommonService;

@Service
public class UserService implements IUserService {

	@Autowired
	IUsersDao userDao;
	
	@Autowired
	IBlogPostDao blogPostDao;
	
	@Autowired
	ICommonService commonService;
	
	@Override
	public Map<String, Object> getCurrentUserDetails(String userName) {
		
		Map<String, Object> data = new HashMap<>();
		Optional<UsersModel> user = null;
		UsersModel currentUsersDetails = null;
		List<BlogPostModel> approvedPosts = null;
		
		//----------- Get Current User Details
		try {
			user = userDao.findByUserName(userName);
			currentUsersDetails = user.get();
			if (currentUsersDetails!=null) {
				data.put("currentUser", currentUsersDetails);
			}else { data.put("user", new ArrayList<>()); }
		} catch (Exception e) {}
		
		//----------- Get all approved post
		try {
			approvedPosts = blogPostDao.findByIsApprovedTrueOrderByPostDateDesc();
			if (approvedPosts!=null) {
				data.put("approvedPosts", approvedPosts);
			}else { data.put("approvedPost", new ArrayList<>()); }
		} catch (Exception e) {}
		 

		return data;
	}

	@Override
	public Map<String, Object> saveUser(UsersModel unsavedUser) {
		Map<String, Object> data = new HashMap<>();
		List<String> message = new ArrayList<>();
		List<String> errorMessage = new ArrayList<>();
		List<BlogPostModel> approvedPosts = null;
		UsersModel savedUser = null;
		String pwd = null;
		
		if (unsavedUser.getUserName()!=null) {
			//--------- Check if this username already exists?
			Optional<UsersModel> alreadyEsistsUser = userDao.findByUserName(unsavedUser.getUserName());
			if (alreadyEsistsUser.isPresent()) {
				errorMessage.add("Sorry, this user already exist, please try unique username...");
			}else {
				pwd = commonService.encodeString(unsavedUser.getPassword(), "sqr");
				
				unsavedUser.setActive(false);
				unsavedUser.setRoles("ROLE_USER");
				unsavedUser.setPassword(pwd);
				unsavedUser.setSignupDate(new Date(System.currentTimeMillis()));
				
				try {
					savedUser = userDao.save(unsavedUser);
					data.put("savedUser", savedUser);
					message.add("Your reagistration is on progress, please wait for admin approval...");
				} catch (Exception e) {e.printStackTrace();}
			}
		}

		//----------- Get all approved post
		try {
			approvedPosts = blogPostDao.findByIsApprovedTrueOrderByPostDateDesc();
			if (approvedPosts!=null) {
				data.put("approvedPosts", approvedPosts);
			}else { data.put("approvedPost", new ArrayList<>()); }
		} catch (Exception e) {}
		data.put("approvedPosts", approvedPosts);
		data.put("message", message);
		data.put("errorMessage", errorMessage);
		
		return data;
	}

	@Override
	public Map<String, Object> getAllDataForAdmin() {
		
		Map<String, Object> data = new HashMap<>();
		List<UsersModel> pendingUsers = (List<UsersModel>) userDao.findByRolesAndActiveFalseOrderBySignupDate("ROLE_USER");
		List<UsersModel> approvedUsers = (List<UsersModel>) userDao.findByRolesAndActiveTrueOrderBySignupDate("ROLE_USER");
		List<BlogPostModel> pendingBlogs = blogPostDao.findByIsApprovedFalseOrderByPostDateDesc();
		List<BlogPostModel> approvedPosts = blogPostDao.findByIsApprovedTrueOrderByPostDateDesc();
		
		data.put("pendingUsers", pendingUsers);
		data.put("approvedUsers", approvedUsers);
		data.put("pendingBlogs", pendingBlogs);
		data.put("approvedPosts", approvedPosts);
		
		return data;
	}

	@Override
	public Map<String, Object> changeActivationStatus(Boolean activeStatus, int id) {
		Map<String, Object> data = new HashMap<>();
		int updatedRow = userDao.activateUser(activeStatus, id);

		if (updatedRow>0) {
			data.put("result", "successful");
		}else {
			data.put("result", "failure");
		}
		return data;
	}

	@Override
	public Map<String, Object> deleteUser(int id) {
		Map<String, Object> data = new HashMap<>();
		UsersModel user = userDao.findById(id);
		
		if (user!=null) {
			userDao.delete(user);
			data.put("result", "successful");
		}else {
			data.put("result", "failure");
		}

		return data;
	}


}
