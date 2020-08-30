package com.square.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.square.service.common.CurrentUser;

@Controller
public class UserController {

	@GetMapping("/user")
	public String user() {
		String username = CurrentUser.getCurrentUser();
		System.out.println("Inside User --------------------------------------- " + username);
		return "user";
	}
	
}
