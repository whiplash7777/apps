package com.vksprojects.blog.services.impl;

import java.util.Collection;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.vksprojects.blog.entities.User;
import com.vksprojects.blog.entities.UserRoles;


@Component
public class CustomUserDetails implements UserDetails {
	
@Autowired
private User user;
	//using constructor injection
	
	 private String name;
	 private String password;
	 private List<GrantedAuthority> role;
	
	 
	
	public CustomUserDetails(User user) {
		this.name=user.getName();
		this.password=user.getPassword();
		this.role = user.getRole().stream()
				.map((role) -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
		
		
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return role;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
