package com.akshay.blog.services;

import java.util.List;

import com.akshay.blog.entities.Category;
import com.akshay.blog.payloads.CategoryDto;

public interface CategoryService {
	
	// create
	public CategoryDto  createCategory(CategoryDto categoryDto);
	
	//update
	
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//delete
 public void  deleteCategory(Integer categoryId);
	
	//get by id
 public CategoryDto getCategoryByCategoryId (Integer categoryId);
 
	//getAll
 public List<CategoryDto> getAllCategories ();
}