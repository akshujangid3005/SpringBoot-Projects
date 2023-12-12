package com.akshay.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshay.blog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	
}
