package com.kodbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodbook.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long>
{

	public Users findByEmail(String email);

	public Users findByUsername(String username);

}
