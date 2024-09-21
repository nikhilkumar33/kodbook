package com.kodbook.controller;


import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kodbook.entities.Post;
import com.kodbook.entities.Users;
import com.kodbook.services.PostService;
import com.kodbook.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	@Autowired
	UsersService userv;
	
	@Autowired
	PostService pserv;

	@PostMapping("/signup")
	public String signUp(@ModelAttribute Users user) {
		
		if (userv.emailExists(user.getEmail()) == true) {
			if (userv.usernameExists(user.getUsername()) == true) {
				userv.addUsers(user);
				return "signupsuccess";
			} else {
				return "usernamefail";
			}
		} else {
			return "signupfail";
		}
	}
		/* Alternative for the register
		boolean status = userv.userExists(user.getUsername(), user.getEmail());
		if (status == false) {
			userv.addUsers(user);
			return "signupsuccess";
		} else {
			return "signupfail";
		}
	}*/

	@PostMapping("/login")
	public String login(@RequestParam String text, @RequestParam String password,Model model,HttpSession session) {
		if (userv.validateUser(text, password) == true) 
		{
			Users user=userv.getUser(text);
			String username=user.getUsername();
			session.setAttribute("username", username);
			model.addAttribute("session", session);
			
			model.addAttribute("user", user);
			
			List<Post> allposts=pserv.fetchAllPosts();
			model.addAttribute("allposts", allposts);
			return "home";
		} else {
			return "loginfail";
		}
	}
	
	@PostMapping("/updateprofile")
	public String updateProfile(@RequestParam String dob,@RequestParam String gender,
			@RequestParam String city,@RequestParam String bio,@RequestParam String college,
			@RequestParam String linkedin,@RequestParam String github,
			@RequestParam("profilePic") MultipartFile profilePic,Model model,HttpSession session)
	{
		//Here we get the username using session and it will return an object so here we hv performing casting
		String username=(String)session.getAttribute("username");
		Users user=userv.getUser(username);
		
		user.setDob(dob);
		user.setGender(gender);
		user.setCity(city);
		user.setBio(bio);
		user.setCollege(college);
		user.setLinkedin(linkedin);
		user.setGithub(github);
		
		try {
			user.setProfilePic(profilePic.getBytes());
		}
		catch(IOException e)
		{
			e.printStackTrace(); 
		}
		
		userv.updateUser(user);
		model.addAttribute("user", user);
		return "myprofile";
	}
	
	@GetMapping("/myprofile")
	public String myProfile(HttpSession session,Model model)
	{
		String username=(String)session.getAttribute("username");
		Users user=userv.getUser(username);
		model.addAttribute("user", user);
		return "myprofile";
	}
}
