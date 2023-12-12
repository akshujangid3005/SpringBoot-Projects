package com.akshay.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.blog.entities.User;
import com.akshay.blog.payloads.UserDto;
import com.akshay.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//POST - create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		
		UserDto createUserdto=this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserdto,HttpStatus.CREATED);
	}
	
	// PUT - update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, 
			                                  @PathVariable Integer userId)
	{   
		    
	UserDto updatedUser	=this.userService.updateUser(userDto, userId);
	return  ResponseEntity.ok(updatedUser);
	}
	
	
	// DELETE - delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
		
		this.userService.deleteUser(userId);
		return  null;
	}
	//GET - get users
	@GetMapping("/")
	public ResponseEntity <List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
		
	}
	// get by Id users
	@GetMapping("/{userId}")
	public ResponseEntity <UserDto> getUserByUserId(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	
	
}
}