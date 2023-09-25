package com.vksprojects.blog.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vksprojects.blog.entities.Comment;
import com.vksprojects.blog.entities.Post;
import com.vksprojects.blog.entities.User;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
	
//	Set<Comment> commentByPost(Post post);

	Set<Comment> findByUser(User user);
//
}
