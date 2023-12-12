package com.akshay.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.blog.entities.Post;
import com.akshay.blog.payloads.PostDto;
import com.akshay.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity< PostDto > createPost(@RequestBody PostDto postDto,
			                                    @PathVariable Integer userId,
			                                    @PathVariable Integer categoryId)
	{
		PostDto createpost=this.postService.createPost(postDto, categoryId, userId);
		
		return new ResponseEntity<PostDto>(createpost,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser( @PathVariable Integer userId)
	{
	 List<PostDto> posts=this.postService.getPostByUser(userId);	
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory( @PathVariable Integer categoryId)
	{
	 List<PostDto> posts=this.postService.getPostBycategory(categoryId);	
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
}
