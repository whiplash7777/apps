package com.vksprojects.blog.services;

import java.util.List;

import com.vksprojects.blog.payloads.CategoryDto;

public interface CategoryService {
	
	//create
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId );
	
	
	//delete
	void deleteCategory(Integer categoryId);
	
	
	//get all
	List<CategoryDto> getCategories();
	

	//get by id
	CategoryDto getCategory(Integer categoryId);
}
