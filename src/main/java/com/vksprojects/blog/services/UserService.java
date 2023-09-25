package com.vksprojects.blog.services;

import java.util.List;

import com.vksprojects.blog.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userid);
	UserDto getUserById( Integer userid);
	List<UserDto> getAllUsers();
	
	void delUser(Integer userId);

}
