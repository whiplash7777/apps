package com.vksprojects.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vksprojects.blog.entities.Comment;
import com.vksprojects.blog.entities.Post;
import com.vksprojects.blog.entities.User;
import com.vksprojects.blog.exceptions.ResourceNotFoundException;
import com.vksprojects.blog.payloads.CommentDto;
import com.vksprojects.blog.repositories.CommentRepo;
import com.vksprojects.blog.repositories.PostRepo;
import com.vksprojects.blog.repositories.UserRepo;
import com.vksprojects.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
	Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "post Id", postId));
	Comment map = this.modelMapper.map(commentDto, Comment.class);
	map.setPost(post);
	Comment save = this.commentRepo.save(map);
	CommentDto dto = this.modelMapper.map(save, CommentDto.class);
	return dto;
	}

//	@Override
//	public CommentDto updateComment(CommentDto commentDto, Integer postId) {
//		Post orElseThrow = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "post Id", postId));
//		Comment map = this.modelMapper.map(commentDto, Comment.class);
//		map.setPost(orElseThrow);
//		Comment save = this.commentRepo.save(map);
//		return this.modelMapper.map(save, CommentDto.class);
//		
//		
//		 
//		
//	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment orElseThrow = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment ID", commentId));
		this.commentRepo.delete(orElseThrow);
			
	}

	@Override
	public Set<CommentDto> commentByUser(Integer userId){
		User orElseThrow = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
		Set<Comment> findByUser = this.commentRepo.findByUser(orElseThrow);
		Set<CommentDto> collect = findByUser.stream().map((e) -> this.modelMapper.map(e, CommentDto.class)).collect(Collectors.toSet());
		return collect;
		
	}
//
//	@Override
//	public Set<CommentDto> commentByPost(Integer postId) {
//		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
//		Set<Comment> commentByPost = this.commentRepo.commentByPost(post);
//		Set<CommentDto> collect = commentByPost.stream().map((e) -> this.modelMapper.map(e, CommentDto.class)).collect(Collectors.toSet());
//		
//		
//		return collect;
//	}

}
