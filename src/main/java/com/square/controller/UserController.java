package com.square.controller;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		mv.setViewName("user");
		mv.addObject("currentUser",  data.get("currentUser"));
		mv.addObject("approvedPosts", data.get("approvedPosts"));
		return mv;
	}
	
	@PostMapping("/user/add")
	public ModelAndView addUser(@ModelAttribute("user") UsersModel user) {
		
		ModelAndView mv  = new ModelAndView();
		Map<String, Object> data = new HashMap<>();
		
		data = userService.saveUser(user);
		mv.setViewName("index");
		mv.addObject("currentUser",  data.get("currentUser"));
		mv.addObject("approvedPosts", data.get("approvedPosts"));
		mv.addObject("messages", data.get("message"));
		mv.addObject("errorMessage", data.get("errorMessage"));
		
		return mv;
	}
	
	@GetMapping("/user/approve/{id}")
	public ModelAndView userApprove(@PathVariable int id) {
				
		ModelAndView mv = new ModelAndView();
		Map<String, Object> data = new HashMap<>();
		
		data = userService.changeActivationStatus(true, id);
		
		if (data.get("result").equals("successful")) {
			data = userService.getAllDataForAdmin();
		}else {
			data = userService.getAllDataForAdmin();
		}
		
		mv.setViewName("admin");
		mv.addObject("pendingUsers", data.get("pendingUsers"));
		mv.addObject("approvedUsers", data.get("approvedUsers"));
		mv.addObject("pendingBlogs", data.get("pendingBlogs"));
		mv.addObject("approvedPosts", data.get("approvedPosts"));
		
		return mv;
	}
	
	@GetMapping("/user/block/{id}")
	public ModelAndView userBlock(@PathVariable int id) {
				
		ModelAndView mv = new ModelAndView();
		Map<String, Object> data = new HashMap<>();
		
		data = userService.changeActivationStatus(false, id);
		
		if (data.get("result").equals("successful")) {
			data = userService.getAllDataForAdmin();
		}else {
			data = userService.getAllDataForAdmin();
		}
		
		mv.setViewName("admin");
		mv.addObject("pendingUsers", data.get("pendingUsers"));
		mv.addObject("approvedUsers", data.get("approvedUsers"));
		mv.addObject("pendingBlogs", data.get("pendingBlogs"));
		mv.addObject("approvedPosts", data.get("approvedPosts"));
		
		return mv;
	}
	
	
	@GetMapping("/user/delete/{id}")
	public ModelAndView userDelete(@PathVariable int id) {
				
		ModelAndView mv = new ModelAndView();
		Map<String, Object> data = new HashMap<>();
		
		data = userService.deleteUser(id);
		
		if (data.get("result").equals("successful")) {
			data = userService.getAllDataForAdmin();
		}else {
			data = userService.getAllDataForAdmin();
		}
		
		mv.setViewName("admin");
		mv.addObject("pendingUsers", data.get("pendingUsers"));
		mv.addObject("approvedUsers", data.get("approvedUsers"));
		mv.addObject("pendingBlogs", data.get("pendingBlogs"));
		mv.addObject("approvedPosts", data.get("approvedPosts"));
		
		return mv;
	}
	
	
}
