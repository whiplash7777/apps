package com.vksprojects.blog.services;

import java.util.List;

import com.vksprojects.blog.entities.Post;
import com.vksprojects.blog.payloads.CategoryDto;
import com.vksprojects.blog.payloads.PostDto;
import com.vksprojects.blog.payloads.PostResponse;

public interface PostService {
	
	//create
	
		PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
		
		//update
		PostDto updatePost(PostDto postDto, Integer postId );
		
		
		//delete
		void deletePost(Integer postId);
		
		
		//get all
		PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy, String sortDir);
		

		//get by id
		PostDto getPostById(Integer postId);
		
		//get all post in a category
		
		List<PostDto> getPostByCategory(Integer categoryId);
		
		//get all post by user
		
		List<PostDto> getPostsByUser(Integer userId);
		
		//search 
		
		List<PostDto> searchPost(String keyword);
		
	}


