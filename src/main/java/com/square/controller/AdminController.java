package com.square.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.square.model.UsersModel;
import com.square.service.IUserService;

@Controller
public class AdminController {

	@Autowired
	IUserService userService;
	
	@GetMapping("/admin")
	public ModelAndView admin() {
		
		ModelAndView mv = new ModelAndView();
		Map<String, Object> data = new HashMap<>();
		data = userService.getAllDataForAdmin();
		
		mv.setViewName("admin");
		mv.addObject("pendingUsers", data.get("pendingUsers"));
		mv.addObject("approvedUsers", data.get("approvedUsers"));
		mv.addObject("pendingBlogs", data.get("pendingBlogs"));
		mv.addObject("approvedPosts", data.get("approvedPosts"));
		
		
		return mv;
	}
	
	@PostMapping("/admin/create")
	public ModelAndView adminCreate(@ModelAttribute("user") UsersModel admin) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> data = new HashMap<>();
		
		mv.setViewName("admin");
		
		return mv;
	}
	
}
