package com.square.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.square.model.BlogPostModel;
import com.square.service.IBlogService;
import com.square.service.IUserService;

@Controller
public class BlogController {

	//,"/blog/remove/{id}"
	
	@Autowired
	IBlogService blogService;
	
	@Autowired
	IUserService userService;
	
	@GetMapping("/blog/approve/{id}")
	public ModelAndView blogApprove(@PathVariable int id) {
				
		ModelAndView mv = new ModelAndView();
		Map<String, Object> data = new HashMap<>();
		List<String> message = new ArrayList<>();
		List<String> errorMessage = new ArrayList<>();
		
		data = blogService.changeApprovalStatus(true, id);
		
		if (data.get("result").equals("successful")) {
			message.add("Successfully approved posts.");
			data = userService.getAllDataForAdmin();
		}else {
			errorMessage.add("Failed to approve posts.");
			data = userService.getAllDataForAdmin();
		}
		
		mv.setViewName("admin");
		mv.addObject("pendingUsers", data.get("pendingUsers"));
		mv.addObject("approvedUsers", data.get("approvedUsers"));
		mv.addObject("pendingBlogs", data.get("pendingBlogs"));
		mv.addObject("approvedPosts", data.get("approvedPosts"));
		mv.addObject("messages", message);
		mv.addObject("errorMessage", errorMessage);
		
		return mv;
	}
	
	@GetMapping("/blog/remove/{id}")
	public ModelAndView removeBlocg(@PathVariable int id) {
				
		ModelAndView mv = new ModelAndView();
		Map<String, Object> data = new HashMap<>();
		List<String> message = new ArrayList<>();
		List<String> errorMessage = new ArrayList<>();
		
		data = blogService.deleteBlog(id);
		
		if (data.get("result").equals("successful")) {
			message.add("Successfully removed posts.");
			data = userService.getAllDataForAdmin();
		}else {
			errorMessage.add("Failed to remove posts.");
			data = userService.getAllDataForAdmin();
		}
		
		mv.setViewName("admin");
		mv.addObject("pendingUsers", data.get("pendingUsers"));
		mv.addObject("approvedUsers", data.get("approvedUsers"));
		mv.addObject("pendingBlogs", data.get("pendingBlogs"));
		mv.addObject("approvedPosts", data.get("approvedPosts"));
		mv.addObject("messages", message);
		mv.addObject("errorMessage", errorMessage);
		
		return mv;
	}
	
	@GetMapping("/blog/remove_by_user/{id}")
	public ModelAndView removeBlocgByUser(@PathVariable int id) {
				
		ModelAndView mv = new ModelAndView();
		Map<String, Object> data = new HashMap<>();
		List<String> message = new ArrayList<>();
		List<String> errorMessage = new ArrayList<>();
		
		data = blogService.deleteBlog(id);
		
		if (data.get("result").equals("successful")) {
			message.add("Successfully removed posts.");
			data = userService.getAllDataForAdmin();
		}else {
			errorMessage.add("Failed to remove posts.");
			data = userService.getAllDataForAdmin();
		}
		
		mv.setViewName("user");
		mv.addObject("pendingUsers", data.get("pendingUsers"));
		mv.addObject("approvedUsers", data.get("approvedUsers"));
		mv.addObject("pendingBlogs", data.get("pendingBlogs"));
		mv.addObject("approvedPosts", data.get("approvedPosts"));
		mv.addObject("messages", message);
		mv.addObject("errorMessage", errorMessage);
		
		return mv;
	}
		
	@PostMapping("/blog/create_by_admin")
	public ModelAndView createBlogByAdmin(@ModelAttribute("blog") BlogPostModel blog) {
				
		ModelAndView mv = new ModelAndView();
		Map<String, Object> data = new HashMap<>();
		List<String> message = new ArrayList<>();
		List<String> errorMessage = new ArrayList<>();
		
		data = blogService.saveBlog(blog);
		
		if (data.get("result").equals("successful")) {
			message.add("Successfully created posts.");
			data = userService.getAllDataForAdmin();
		}else {
			errorMessage.add("Failed to create posts.");
			data = userService.getAllDataForAdmin();
		}
		
		mv.setViewName("admin");
		mv.addObject("pendingUsers", data.get("pendingUsers"));
		mv.addObject("approvedUsers", data.get("approvedUsers"));
		mv.addObject("pendingBlogs", data.get("pendingBlogs"));
		mv.addObject("approvedPosts", data.get("approvedPosts"));
		mv.addObject("messages", message);
		mv.addObject("errorMessage", errorMessage);
		
		return mv;
	}
	
	@PostMapping("/blog/create_by_user")
	public ModelAndView createBlogByUser(@ModelAttribute("blog") BlogPostModel blog) {
				
		ModelAndView mv = new ModelAndView();
		Map<String, Object> data = new HashMap<>();
		List<String> message = new ArrayList<>();
		List<String> errorMessage = new ArrayList<>();
		
		data = blogService.saveBlogByUser(blog);
		
		if (data.get("result").equals("successful")) {
			message.add("Successfully created posts. Sending for approval");
			data = userService.getAllDataForAdmin();
		}else {
			errorMessage.add("Failed to create posts.");
			data = userService.getAllDataForAdmin();
		}
		
		mv.setViewName("user");
		mv.addObject("pendingUsers", data.get("pendingUsers"));
		mv.addObject("approvedUsers", data.get("approvedUsers"));
		mv.addObject("pendingBlogs", data.get("pendingBlogs"));
		mv.addObject("approvedPosts", data.get("approvedPosts"));
		mv.addObject("messages", message);
		mv.addObject("errorMessage", errorMessage);
		
		return mv;
	}
		
}
