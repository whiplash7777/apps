package com.vksprojects.blog.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vksprojects.blog.entities.Comment;
import com.vksprojects.blog.entities.Post;
import com.vksprojects.blog.payloads.ApiResponse;
import com.vksprojects.blog.payloads.CommentDto;
import com.vksprojects.blog.repositories.CommentRepo;
import com.vksprojects.blog.repositories.PostRepo;
import com.vksprojects.blog.services.CommentService;
import com.vksprojects.blog.services.PostService;



@RestController
@RequestMapping("/api/")
public class CommentController {
	
	
	
	@Autowired
	private CommentService commentService;

	
	//create
	
	@PostMapping("/posts/{postId}/comment")
	private ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId ){
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
		
	}
	
//	
//	//update
//	@PutMapping("/posts/{postId}")
//	private ResponseEntity<CommentDto> updatecomment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
//		CommentDto updateComment = this.commentService.updateComment(commentDto,postId );
//		return new ResponseEntity<CommentDto>(updateComment,HttpStatus.OK);
//		
//	}
	
	
	
	
	//delete\
	@DeleteMapping("/{commentId}")
	private ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully",true),HttpStatus.OK);
		
	}
	
	
	
	//get comment by user
	@GetMapping("/comment/{userId}")
	private ResponseEntity<Set<CommentDto>> getCommentByUser(@PathVariable Integer userId){
		Set<CommentDto> byUser = this.commentService.commentByUser(userId);
		return new ResponseEntity<Set<CommentDto>>(byUser,HttpStatus.OK);
	}
	
	//get Comment by post
//	@GetMapping("/comment/{postId}")
//	private ResponseEntity<Set<CommentDto>> getCommentByPost(@PathVariable Integer postId){
//		Set<CommentDto> byPost = this.commentService.commentByPost(postId);
//		return new ResponseEntity<Set<CommentDto>>(byPost,HttpStatus.OK);
//		
//	}
//	
//	

}
