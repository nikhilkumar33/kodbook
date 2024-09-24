package com.kodbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

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
	public String openCreatePost(HttpSession session)
	{
		if(session.getAttribute("username")!=null) {
			return "createpost";
		}
		else {
			return "index";
		}
	}
	
	@GetMapping("/openeditprofile")
	public String editProfile(HttpSession session)
	{
		if(session.getAttribute("username")!=null)
			return "editprofile";
		else
			return "index";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "index";
	}
}
