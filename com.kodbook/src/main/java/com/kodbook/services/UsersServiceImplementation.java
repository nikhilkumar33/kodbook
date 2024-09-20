package com.kodbook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.kodbook.entities.Users;
import com.kodbook.repositories.UsersRepository;

@Service
public class UsersServiceImplementation implements UsersService {

	@Autowired
	UsersRepository urepo;

	@Override
	public boolean emailExists(String email) {
		Users user = urepo.findByEmail(email);
		if (user == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean usernameExists(String username) {
		Users user = urepo.findByUsername(username);
		if (user == null) {
			return true;
		} else {
			return false;
		}
	}
	/*
	@Override
	public boolean userExists(String username, String email) {
		Users user1 = urepo.findByEmail(email);
		Users user2 = urepo.findByUsername(username);
		if (user1 != null || user2 != null) {

			return true;
		} else {
			return false;
		}

	}*/
	
	@Override
	public void addUsers(Users user) {
		urepo.save(user);

	}

	@Override
	public boolean validateUser(String text, String password) {
		Users user = urepo.findByEmail(text);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		} else {
			Users user2 = urepo.findByUsername(text);
			if (user2 != null) {
				if (user2.getPassword().equals(password)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	@Override
	public Users getUser(String text) {
		// TODO Auto-generated method stub
		Users user1=urepo.findByEmail(text);
		if(user1==null)
		{
			Users user2=urepo.findByUsername(text);
			return user2;
		}
		return user1;
	}

	@Override
	public void updateUser(Users user) {
		urepo.save(user);
		
	}

	

}
