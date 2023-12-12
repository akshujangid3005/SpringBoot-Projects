package com.akshay.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshay.blog.dao.CategoryRepository;
import com.akshay.blog.dao.PostRepository;
import com.akshay.blog.dao.UserRepository;
import com.akshay.blog.entities.Category;
import com.akshay.blog.entities.Post;
import com.akshay.blog.entities.User;
import com.akshay.blog.exceptions.ResourceNotFoundException;
import com.akshay.blog.payloads.PostDto;
import com.akshay.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostDto createPost(PostDto postDto,Integer categoryId,Integer userId) {
		
		User user = this.userRepository.findById(userId)
		.orElseThrow(()->  new ResourceNotFoundException("User", "userId", userId));  
		
		Category  category = this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("category", "category id ", categoryId));
		
		Post post=this.modelMapper.map(postDto,Post.class);
		post.setPostImage("default.png");
		post.setPostDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
	Post newPost= 	this.postRepository.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletPost(Integer postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPostBycategory(Integer categoryId) {
		Category cat=this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
     List<Post> posts =this.postRepository.findByCategory(cat);
     List<PostDto> postDtos= 	posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
     return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		   User  user =this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		    List<Post> userPosts  = this.postRepository.findByUser(user);
		  
		    List<PostDto> postDtos=userPosts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		    
		    
		return postDtos;
	}

}
