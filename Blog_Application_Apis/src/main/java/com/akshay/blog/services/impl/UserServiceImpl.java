package com.akshay.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshay.blog.dao.UserRepository;
import com.akshay.blog.entities.User;
import com.akshay.blog.exceptions.ResourceNotFoundException;
import com.akshay.blog.payloads.UserDto;
import com.akshay.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	// CREATE USER 
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user= this.dtoToUser(userDto) ;
	User savedUser	=this.userRepository.save(user);
		
		return this.userToDto(savedUser);
	}

	// UPDATE USER DETAILS 
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));  
		 
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
	User updateUser	=this.userRepository.save(user);
		
		return userDto;
	}
	
	// GET BY ID 

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId)); 
		return this.userToDto(user);
	}
	
	
	// GET ALL USERS

	@Override
	public List<UserDto> getAllUsers() {
	     
	List<User> users=	this.userRepository.findAll();
	 List<UserDto> userDtos= users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
	  
	return userDtos ;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId)); 
		this.userRepository.delete(user);

	}
	
	public User dtoToUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		/*
		 * user.setId(userDto.getId()); user.setName(userDto.getName());
		 * user.setEmail(userDto.getEmail()); user.setPassword(userDto.getPassword());
		 * user.setAbout(userDto.getAbout());
		 */
		return user;
		
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto= this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
		
	}
	
	

}
