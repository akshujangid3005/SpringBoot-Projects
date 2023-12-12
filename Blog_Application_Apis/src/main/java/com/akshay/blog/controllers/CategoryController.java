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
import org.springframework.web.bind.annotation.RestController;

import com.akshay.blog.payloads.CategoryDto;
import com.akshay.blog.payloads.UserDto;
import com.akshay.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	// create 
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory( @Valid @RequestBody CategoryDto categoryDto){
		
	CategoryDto createCategory=	this.categoryService.createCategory(categoryDto);
	return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
		
	}
	
	// update 
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, 
			                                  @PathVariable Integer categoryId)
	{   
		    
		CategoryDto updateCategory=	this.categoryService.updateCategory(categoryDto ,categoryId);
		return ResponseEntity.ok(updateCategory);
	}
	
   //	delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer categoryId){
		
		this.categoryService.deleteCategory(categoryId);
		return  null;
	}
	//get by id
	@GetMapping("/{categoryId}")
	public ResponseEntity <CategoryDto> getCategoryByCategoryId(@PathVariable Integer categoryId){
		return ResponseEntity.ok(this.categoryService.getCategoryByCategoryId(categoryId));
	}
	// get All
	@GetMapping("/")
	public ResponseEntity <List<CategoryDto>> getAllCategory(){
		return ResponseEntity.ok(this.categoryService.getAllCategories());
		
	}
}
