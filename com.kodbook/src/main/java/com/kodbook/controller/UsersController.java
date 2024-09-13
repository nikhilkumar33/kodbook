package com.kodbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodbook.entities.Users;
import com.kodbook.services.UsersService;

@Controller
public class UsersController {
	@Autowired
	UsersService userv;

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
	public String login(@RequestParam String text, @RequestParam String password) {
		if (userv.validateUser(text, password) == true) {
			return "home";
		} else {
			return "loginfail";
		}
	}
}
