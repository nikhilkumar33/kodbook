package com.kodbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {

	@GetMapping("/openSignUp")
	public String openSignUp()
	{
		return "signup";
	}
	@GetMapping("/openResetPassword")
	public String openResetPassword()
	{
		return "resetpassword";
	}
	@GetMapping("/opencreatepost")
	public String openCreatePost()
	{
		return "createpost";
	}
	/*
	@GetMapping("/opennewsfeed")
	public String openNewsFeed()
	{
		return "home";
	}*/
}
