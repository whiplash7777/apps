package com.vksprojects.blog.services.impl;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vksprojects.blog.entities.Category;
import com.vksprojects.blog.entities.Post;
import com.vksprojects.blog.entities.User;
import com.vksprojects.blog.exceptions.ResourceNotFoundException;
import com.vksprojects.blog.payloads.CategoryDto;
import com.vksprojects.blog.payloads.PostDto;
import com.vksprojects.blog.payloads.PostResponse;
import com.vksprojects.blog.repositories.CategoryRepo;
import com.vksprojects.blog.repositories.PostRepo;
import com.vksprojects.blog.repositories.UserRepo;
import com.vksprojects.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
		
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		
		Category category= this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		Post newPost = this.modelMapper.map(postDto, Post.class);
		newPost.setImageName("default.png");
		newPost.setAddedDate(new Date());
		newPost.setUser(user);
		newPost.setCategory(category);
		
		Post post = this.postRepo.save(newPost);
		
		return this.modelMapper.map(post, PostDto.class);
			
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post upPost= this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		
		upPost.setTitle(postDto.getTitle());
		upPost.setContent(postDto.getContent());
		upPost.setImageName(postDto.getImageName());
		Post save = this.postRepo.save(upPost);
		PostDto map = this.modelMapper.map(save, PostDto.class);
		return map;
		
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
		this.postRepo.delete(post);

	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		//using ternary operation
		
		Sort sort= (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		//using if else statement
//		Sort sort = null;
//		if (sortDir.equalsIgnoreCase("asc")) {
//			
//			sort= Sort.by(sortBy).ascending();
//			
//		} else {
//			sort= Sort.by(sortBy).descending();
//
//		}
		
		
		PageRequest of = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> findAll = this.postRepo.findAll(of);
		List<Post> content = findAll.getContent();
		List<PostDto> collect = findAll.stream().map((e)-> this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse= new PostResponse();
		postResponse.setContent(collect);
		postResponse.setPageNumber(findAll.getNumber());
		postResponse.setPagesize(findAll.getSize());
		postResponse.setTotalElements(findAll.getTotalElements());
		postResponse.setTotalPages(findAll.getTotalPages());
		postResponse.setLastPage(findAll.isLast());
		
		return postResponse;
	
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post posts = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "post Id", postId));
		PostDto map = this.modelMapper.map(posts, PostDto.class);
		return map;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category= this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("category", "category Id", categoryId));
		List<Post> findById = this.postRepo.findByCategory(category);
		List<PostDto> map = findById.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
	return map;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "user Id", userId));
		List<Post> list = this.postRepo.findByUser(user);
		List<PostDto> collect = list.stream().map((post) -> this.modelMapper
				.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}


	@Override
	public List<PostDto> searchPost(String keyword) {
		
		List<Post> titleCont = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> collect = titleCont.stream()
		.map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return collect;
	}

}
