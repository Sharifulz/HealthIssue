package com.square.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.square.dao.IUsersDao;
import com.square.model.BlogPostModel;
import com.square.model.CommentsModel;
import com.square.model.UsersModel;
import com.square.service.common.CurrentUser;
import com.square.service.common.IEncoderDecoder;

@Controller
public class UserController {
	@Autowired
	IUsersDao userDao;
	
	@Autowired
	IEncoderDecoder encoderDecoder;
	
	@GetMapping("/user")
	public ModelAndView user() {
		String username = CurrentUser.getCurrentUser();
		
		System.out.println("Inside ADD User success login--------------------------------------- " + username);
		List<UsersModel> list = userDao.findAll();
		ModelAndView mv  = new ModelAndView();
		List<BlogPostModel> postsList = list.get(0).getPostsList();
		
		List<CommentsModel> commentsList = postsList.get(0).getCommentsList();
		System.out.println("Comments list found size: ------------- "+ commentsList.size());
		
		System.out.println(postsList.size());
		System.out.println(list.get(0).getUserName());
		mv.setViewName("user");
		mv.addObject("posts", list);
		return mv;
	}
	
	@PostMapping("/user/add")
	public ModelAndView addUser(@ModelAttribute("user") UsersModel user) {
		
		//System.out.println("New user : ------------------- "+ user.getUserName());
		//System.out.println("New user : ------------------- "+ user.getId());
		user.setActive(false);
		user.setRoles("ROLE_USER");
		
		String pwd = encoderDecoder.encodeString(user.getPassword(), "sqr");
		user.setPassword(pwd);
		userDao.save(user);
		
		String username = CurrentUser.getCurrentUser();
		//System.out.println("Inside ADD User --------------------------------------- " + username);
		List<UsersModel> list = userDao.findAll();
		ModelAndView mv  = new ModelAndView();
		//List<BlogPostModel> postsList = list.get(0).getPostsList();
		//System.out.println(postsList.size());
		//System.out.println(list.get(0).getUserName());
		mv.setViewName("index");
		mv.addObject("posts", list);
		return mv;
	}
	
}
