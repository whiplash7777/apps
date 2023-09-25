package com.vksprojects.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vksprojects.blog.entities.User;
import com.vksprojects.blog.repositories.UserRepo;

@Service
public class DatabaseUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;
	
	public DatabaseUserDetailService() {
		
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User findByUser = userRepo.findByUsername(username);
		if(findByUser==null) {
			throw new UsernameNotFoundException("User wioth Username ["+username+"] not found");
		}
		// TODO Auto-generated method stub
		return new CustomUserDetails(findByUser);
	}

}
