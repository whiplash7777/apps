package com.vksprojects.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vksprojects.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	public User findByUsername(String username);

}
