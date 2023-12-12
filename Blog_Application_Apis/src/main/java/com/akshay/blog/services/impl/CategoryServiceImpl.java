package com.akshay.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshay.blog.dao.CategoryRepository;
import com.akshay.blog.entities.Category;
import com.akshay.blog.exceptions.ResourceNotFoundException;
import com.akshay.blog.payloads.CategoryDto;
import com.akshay.blog.payloads.UserDto;
import com.akshay.blog.services.CategoryService;

@Service
public class CategoryServiceImpl  implements CategoryService{
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
	Category cat=	 this.modelMapper.map(categoryDto, Category.class);
     Category addedcat=	this.categoryRepository.save(cat);
		 return this.modelMapper.map(addedcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
	     Category cat =   this.categoryRepository.findById(categoryId)
	        .orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
	        
	        cat.setCategoryTitle(categoryDto.getCategoryTitle());
	        cat.setCategoryDescription(categoryDto.getCategoryDescription());
	        Category updatecategory= this.categoryRepository.save(cat);
	        return this.modelMapper.map(updatecategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));;
			this.categoryRepository.delete(cat);	
		
	}

	@Override
	public CategoryDto getCategoryByCategoryId(Integer categoryId) {
		 Category cat= this.categoryRepository.findById(categoryId)
				 .orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));;
		    return this.modelMapper.map(cat,CategoryDto.class );
	}

	@Override
	public List<CategoryDto> getAllCategories() {
 List<Category> categories=		this.categoryRepository.findAll();
 List<CategoryDto> categoryDtos= categories.stream().map(cat -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());

		return categoryDtos;
	}
	

}
