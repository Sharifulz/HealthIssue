package com.square.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.square.dao.IBlogPostDao;
import com.square.dao.IUsersDao;
import com.square.model.BlogPostModel;
import com.square.model.UsersModel;

@Controller
public class HomeController {
	
	@Autowired
	IUsersDao userDao;
	
	@Autowired
	IBlogPostDao blogPostDao;
	
	@GetMapping("/")
	public ModelAndView home() {
		
		ModelAndView mv  = new ModelAndView();
		List<BlogPostModel> postsList = blogPostDao.findByIsApprovedTrueOrderByPostDateDesc();
		System.out.println("ROOT :---------------> Approved Post Size "+ postsList.size());
		mv.setViewName("index");
		mv.addObject("data", postsList);
		return mv;
	}
	
	@GetMapping("/index")
	public String successfulLogin() {
		return "index";
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv  = new ModelAndView();
		List<BlogPostModel> postsList = blogPostDao.findByIsApprovedTrueOrderByPostDateDesc();
		
		mv.setViewName("index");
		mv.addObject("data", postsList);
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
