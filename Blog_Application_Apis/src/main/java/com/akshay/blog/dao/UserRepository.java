package com.akshay.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshay.blog.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	

}
