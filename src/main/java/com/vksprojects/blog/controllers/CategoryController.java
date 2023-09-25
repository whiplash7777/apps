package com.vksprojects.blog.controllers;

import java.util.List;

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

import com.vksprojects.blog.payloads.ApiResponse;
import com.vksprojects.blog.payloads.CategoryDto;
import com.vksprojects.blog.payloads.UserDto;
import com.vksprojects.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto catDto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(catDto,HttpStatus.CREATED);	
		
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto , @PathVariable Integer catId ){
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
			
	}
	
	
	//delete
	
		@DeleteMapping("/{catId}")
		public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId ){
			 this.categoryService.deleteCategory(catId);
			return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted succesfully",true),HttpStatus.OK);
		}
	
	
	//get
		@GetMapping("/{catId}")
		public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId ){
		 CategoryDto getCategory = this.categoryService.getCategory(catId);
				return new ResponseEntity<CategoryDto>(getCategory,HttpStatus.OK);
		}
		 			
	//get all
		@GetMapping("/")
				
		public ResponseEntity<List<CategoryDto>> getCategories(){
//		return ResponseEntity.ok(this.categoryService.getCategories());
			List<CategoryDto> categories = this.categoryService.getCategories();
			return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
			
	

}
}
