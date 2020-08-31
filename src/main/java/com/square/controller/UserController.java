package com.square.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.square.model.UsersModel;
import com.square.service.IUserService;
import com.square.service.common.CurrentUser;
import com.square.service.common.ICommonService;

@Controller
public class UserController {
	@Autowired
	IUserService userService;
	
	@Autowired
	ICommonService commonService;
	
	@GetMapping("/user")
	public ModelAndView user() {
		
		ModelAndView mv  = new ModelAndView();
		String userName = CurrentUser.getCurrentUser();
		Map<String, Object> data = userService.getCurrentUserDetails(userName);
		
		//List<BlogPostModel> postsList = list.get(0).getPostsList();
		
		//Optional<UsersModel> optionalUser = userDao.findByUserName(username);
		//UsersModel user = optionalUser.get();
		
		//List<CommentsModel> commentsList = postsList.get(0).getCommentsList();
		//System.out.println("Comments list found size: ------------- "+ commentsList.size());
		
		//System.out.println(postsList.size());
		//System.out.println(list.get(0).getUserName());
		mv.setViewName("user");
		mv.addObject("data", data);
		//mv.addObject("posts", list);
		return mv;
	}
	
	@PostMapping("/user/add")
	public ModelAndView addUser(@ModelAttribute("user") UsersModel user) {
		
		Map<String, Object> data = new HashMap<>();
		
		data = userService.saveUser(user);
		
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("user", user);
		return mv;
	}
	
	@GetMapping("/user/delete")
	public ModelAndView deleteUser() {
		System.out.println("DELETE USER CONTOLLER -----------------------------------------> ");
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("admin");
		return mv;
	}
	
	
}
