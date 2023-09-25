package com.vksprojects.blog.payloads;

import com.vksprojects.blog.entities.Post;
import com.vksprojects.blog.entities.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
	
	private int commentId;

	@NotBlank(message = "please provide content")
	private String comments;

	
	
	
	
	
}
