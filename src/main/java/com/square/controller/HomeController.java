package com.square.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.square.dao.IBlogPostDao;
import com.square.dao.IUsersDao;
import com.square.model.BlogPostModel;

@Controller
public class HomeController {
	
	@Autowired
	IUsersDao userDao;
	
	@Autowired
	IBlogPostDao blogPostDao;
	
	@GetMapping("/")
	public ModelAndView home() {
		
		ModelAndView mv  = new ModelAndView();
		List<BlogPostModel> approvedPosts = blogPostDao.findByIsApprovedTrueOrderByPostDateDesc();
		
		mv.setViewName("index");
		mv.addObject("approvedPosts", approvedPosts);
		return mv;
	}
	
	@GetMapping("/index")
	public String successfulLogin() {
		return "index";
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv  = new ModelAndView();
		List<String> warningMessage = new ArrayList<>();
		List<BlogPostModel> approvedPosts = blogPostDao.findByIsApprovedTrueOrderByPostDateDesc();
		warningMessage.add("Please login first to continue...");
		
		mv.setViewName("index");
		mv.addObject("approvedPosts", approvedPosts);
		mv.addObject("warningMessage", warningMessage);
		return mv;
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
