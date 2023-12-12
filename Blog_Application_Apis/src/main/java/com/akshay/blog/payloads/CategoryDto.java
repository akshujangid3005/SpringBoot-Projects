package com.akshay.blog.payloads;

import org.hibernate.validator.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
 public class CategoryDto {

	
	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min=4,max=100, message = "Category Title  must be min 4 and max 100 characters !!")
	private String categoryTitle;
	@NotEmpty
	@Size(min=4,max=1000, message = "Category Title must be min 4 and max 1000 characters !!")
	private String categoryDescription;
	
}
