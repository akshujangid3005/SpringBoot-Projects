package com.akshay.blog.services;

import java.util.List;

import com.akshay.blog.entities.Category;
import com.akshay.blog.entities.Post;
import com.akshay.blog.entities.User;
import com.akshay.blog.payloads.PostDto;

public interface PostService {
	
	//create POST
	
	public PostDto createPost(PostDto postDto,Integer categoryId,Integer userId);
	
	// update POST
	
	public PostDto updatePost(PostDto postDto , Integer postId );
	
	// DELETE POST
	public void deletPost(Integer postId);
	
	
	// GET ALL POST 
    public List<Post>  getAllPost();
    
    // GET ALL POST BY POST ID
    public Post getPostById(Integer postId);

    
    // GET ALL POST BY CATEGORY 
    public List<PostDto> getPostBycategory(Integer categoryId);
    
    // GET ALL POST BY USER 
    public List<PostDto> getPostByUser(Integer userId);
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
