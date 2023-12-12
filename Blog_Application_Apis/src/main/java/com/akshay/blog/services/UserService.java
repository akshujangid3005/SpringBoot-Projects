package com.akshay.blog.services;

import java.util.List;

import com.akshay.blog.entities.User;
import com.akshay.blog.payloads.UserDto;


public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user ,Integer userId);
	
	UserDto getUserById(Integer userId);
	 
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userid);
	
	
	
}
