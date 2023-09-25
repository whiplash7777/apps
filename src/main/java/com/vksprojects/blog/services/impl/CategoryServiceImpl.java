package com.vksprojects.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vksprojects.blog.entities.Category;
import com.vksprojects.blog.exceptions.ResourceNotFoundException;
import com.vksprojects.blog.payloads.CategoryDto;
import com.vksprojects.blog.repositories.CategoryRepo;
import com.vksprojects.blog.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;


	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category catCreate = this.modelMapper.map(categoryDto, Category.class);
		Category updatedCat= this.categoryRepo.save(catCreate);
		return this.modelMapper.map(updatedCat, CategoryDto.class);
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category upcat = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", categoryId));
		
		
		upcat.setCategoryDescription(categoryDto.getCategoryDescription());
		upcat.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category updatedCat = this.categoryRepo.save(upcat);
		return this.modelMapper.map(updatedCat, CategoryDto.class);
		
	}



	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category getCat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
		CategoryDto catDto = this.modelMapper.map(getCat, CategoryDto.class);
		return catDto;
		
	}

	@Override
	public List<CategoryDto> getCategories() {

		List<Category> categories= this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream()
				.map(e -> this.modelMapper.map(e, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
		
}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category delCat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", categoryId));
		
		this.categoryRepo.delete(delCat);
		
	}
}
	
	
	