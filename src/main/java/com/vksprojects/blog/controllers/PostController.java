package com.vksprojects.blog.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vksprojects.blog.config.Appconstants;
import com.vksprojects.blog.entities.Category;
import com.vksprojects.blog.entities.Post;
import com.vksprojects.blog.entities.User;
import com.vksprojects.blog.payloads.ApiResponse;
import com.vksprojects.blog.payloads.CategoryDto;
import com.vksprojects.blog.payloads.PostDto;
import com.vksprojects.blog.payloads.PostResponse;
import com.vksprojects.blog.repositories.CategoryRepo;
import com.vksprojects.blog.repositories.UserRepo;
import com.vksprojects.blog.services.FileService;
import com.vksprojects.blog.services.PostService;
import com.vksprojects.blog.services.impl.PostServiceImpl;

import jakarta.persistence.MappedSuperclass;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
			@PathVariable Integer userId, @PathVariable Integer categoryId){
		
		 PostDto createPost = this.postService.createPost(postDto, userId, categoryId) ;
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
}
	//get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory( @PathVariable Integer categoryId){
		List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(postByCategory,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser ( @PathVariable Integer userId){
		
		List<PostDto> dtos = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(dtos,HttpStatus.OK);
	}
	
	//get all posts
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value= "pageNumber", defaultValue = Appconstants.PAGE_NUMBER ,required = false) Integer pageNumber,
			@RequestParam(value="pagesize", defaultValue = Appconstants.PAGE_SIZE, required = false) Integer pageSize, 
			@RequestParam(value = "sortBy", defaultValue = Appconstants.SORT_BY, required= false) String sortBy ,
			@RequestParam (value = "sortDir", defaultValue = Appconstants.SORT_DIR, required = false) String sortDir)
	
		{
		PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
	}
	
	
	//get post by postID
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		
		PostDto postById = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postById,HttpStatus.OK);
	}
	
	//delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePostById(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ApiResponse("Post deleted Successfully",true);
		
	}
	
	//update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByKeyword(@PathVariable ("keywords") String keywords){
		
		List<PostDto> searchPost = this.postService.searchPost(keywords);
		return new ResponseEntity<List<PostDto>>(searchPost,HttpStatus.OK);
		

	}
	//image upload
	@PostMapping("post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {
		PostDto postById = this.postService.getPostById(postId);
		String uploadImage = this.fileService.uploadImage(path, image);
		
		postById.setImageName(uploadImage);
		PostDto updatePost = this.postService.updatePost(postById, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
		
	}
	
	//post to serve image
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
		
	}
	
	
	
	
	}
