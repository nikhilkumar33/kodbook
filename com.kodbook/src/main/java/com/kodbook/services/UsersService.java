package com.kodbook.services;

import com.kodbook.entities.Users;

public interface UsersService {

	public boolean emailExists(String email);

	public boolean usernameExists(String username);

	public void addUsers(Users user);

	public boolean validateUser(String text, String password);

	//public boolean userExists(String username, String email);


}
