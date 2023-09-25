package com.vksprojects.blog.services;

import java.util.Set;

import com.vksprojects.blog.payloads.CommentDto;

public interface CommentService {
	
	//create
	CommentDto createComment(CommentDto commentDto, Integer postId);
//	
//	//update
//	CommentDto updateComment(CommentDto commentDto,  Integer postId);
	
	
	//delete
	void deleteComment(Integer commentId);
	
	
//	//get all comment by post
//	Set<CommentDto> commentByPost(Integer postId );
	
	

	Set<CommentDto> commentByUser(Integer userId);
	
	

	
	

}
