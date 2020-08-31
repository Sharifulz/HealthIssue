package com.square.service;

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
		Optional<UsersModel> optionalUser = null;
		UsersModel user = null;
		List<BlogPostModel> approvedPost = null;
		
		//----------- Current User Details
		try {
			optionalUser = userDao.findByUserName(userName);
			user = optionalUser.get();
			if (user!=null) {
				user.setPassword("");
				data.put("user", user);
			}else { data.put("user", new ArrayList<>()); }
		} catch (Exception e) {}
		
		//----------- Get all approved post
		try {
			approvedPost = blogPostDao.findByIsApprovedTrueOrderByPostDateDesc();
			if (approvedPost!=null) {
				data.put("approvedPost", approvedPost);
			}else { data.put("approvedPost", new ArrayList<>()); }
		} catch (Exception e) {}
		 

		return data;
	}

	@Override
	public Map<String, Object> saveUser(UsersModel user) {
		Map<String, Object> data = new HashMap<>();
		
		UsersModel users = null;
		String pwd = commonService.encodeString(user.getPassword(), "sqr");
		
		user.setActive(false);
		user.setRoles("ROLE_USER");
		user.setPassword(pwd);
		
		try {
			users = userDao.save(user);
		} catch (Exception e) {e.printStackTrace();}
		
		data.put("user", users);
		
		return data;
	}

	@Override
	public Map<String, Object> getAllDataForAdmin() {
		
		Map<String, Object> data = new HashMap<>();
		List<UsersModel> pendingUsers = (List<UsersModel>) userDao.findByRolesAndActiveFalseOrderBySignupDate("ROLE_USER");
		List<BlogPostModel> pendingBlogs = blogPostDao.findByIsApprovedFalseOrderByPostDateDesc();
		List<BlogPostModel> approvedPosts = blogPostDao.findByIsApprovedTrueOrderByPostDateDesc();
		
		data.put("pendingUsers", pendingUsers);
		data.put("pendingBlogs", pendingBlogs);
		data.put("approvedPosts", approvedPosts);
		
		return data;
	}


}
