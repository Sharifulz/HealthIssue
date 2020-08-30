package com.square.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.square.dao.IUsersDao;
import com.square.model.BlogPostModel;
import com.square.model.UsersModel;

@Controller
public class HomeController {
	
	@Autowired
	IUsersDao userDao;
	
	@GetMapping("/")
	public ModelAndView home() {
		
		List<UsersModel> list = userDao.findAll();
		ModelAndView mv  = new ModelAndView();
		
		List<BlogPostModel> postsList = list.get(0).getPostsList();
		System.out.println(postsList.size());
		System.out.println(list.get(0).getUserName());
		mv.setViewName("index");
		mv.addObject("posts", list);
		return mv;
	}
	
	@GetMapping("/index")
	public String successfulLogin() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "index";
	}
	
	@GetMapping("/noaccess")
	public String noaccess() {
		return "noaccess";
	}
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
}
