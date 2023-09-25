package com.vksprojects.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vksprojects.blog.entities.Category;
import com.vksprojects.blog.entities.Comment;
import com.vksprojects.blog.entities.Post;
import com.vksprojects.blog.entities.User;
import com.vksprojects.blog.payloads.CommentDto;


public interface PostRepo extends JpaRepository<Post,Integer >{
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	
	List<Post> findByTitleContaining(String title);
	

}
