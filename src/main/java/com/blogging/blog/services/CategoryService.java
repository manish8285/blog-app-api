package com.blogging.blog.services;

import java.util.List;

import com.blogging.blog.payloads.CategoryDto;

public interface CategoryService {
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, int catId);
	
	//delete
	void deleteCategory(int catId);
	//get
	CategoryDto getCategory(int catId);
	//get all
	List<CategoryDto> getCategories();
}
