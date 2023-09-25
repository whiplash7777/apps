package com.vksprojects.blog.services.impl;
import com.vksprojects.blog.exceptions.*;



import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vksprojects.blog.entities.User;
import com.vksprojects.blog.payloads.UserDto;
import com.vksprojects.blog.repositories.UserRepo;
import com.vksprojects.blog.services.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	
	@Override
	public UserDto updateUser(UserDto userDto, Integer userid) {
		User user = this.userRepo.findById(userid)
				    .orElseThrow(() -> new ResourceNotFoundException("User","Id",userid));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = this.userRepo.save(user);
		
		UserDto userDto1 = this.userToDto(updatedUser);
		

		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userid) {
		User user = this.userRepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userid));
		
		return this.userToDto(user);
	}

//	@Override
//	public List<UserDto> getAllUsers() {
//	            List<User> users = this.userRepo.findAll();
//	            List<UserDto> userDtos = users.stream()
//	            		                 .map(user -> this.userToDto(user)).collect(Collectors.toList());
//	            
//	            
//		
//		return userDtos;
//	}
	@Override
	public List<UserDto> getAllUsers() {
	            List<User> users = this.userRepo.findAll();
	           List<UserDto> collect = users.stream().map((user) -> this.userToDto(user)).collect(Collectors.toList());
	            		                 
	            
	            
		
		return collect;
	}

	@Override
	public void delUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		this.userRepo.delete(user);

	}
	
	private User dtoToUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
		
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
//		
		
		return user;
		
		
	}
	
	private UserDto userToDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		
//		UserDto userDto= new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		
		
		
		return userDto;
		
		
	}

}
