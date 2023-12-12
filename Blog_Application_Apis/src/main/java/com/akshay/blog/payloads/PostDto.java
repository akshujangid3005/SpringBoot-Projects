package com.akshay.blog.payloads;

import java.util.Date;

import com.akshay.blog.entities.Category;
import com.akshay.blog.entities.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private String postTitle;
	
	private String postContent;
	
	private String postImage;
	
    private Date postDate;
	
	private CategoryDto category;

     private UserDto user;
	
	
	
}
