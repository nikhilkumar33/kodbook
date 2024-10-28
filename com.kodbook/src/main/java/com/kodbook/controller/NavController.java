package com.kodbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kodbook.entities.Users;
import com.kodbook.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavController {

	@Autowired
	UsersService userv;
	
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
	public String openCreatePost(HttpSession session,Model model)
	{
		if(session.getAttribute("username")!=null) {
			String username = (String) session.getAttribute("username");
		    Users user = userv.getUser(username);
			model.addAttribute("user", user);
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
