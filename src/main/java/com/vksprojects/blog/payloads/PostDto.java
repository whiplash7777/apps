package com.vksprojects.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vksprojects.blog.entities.Category;
import com.vksprojects.blog.entities.Comment;
import com.vksprojects.blog.entities.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	

	private Integer postId;
	
	@NotBlank(message = "please provide title")
	private String title;
	
	@NotBlank(message = "please provide content")
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
	
	

}

	
