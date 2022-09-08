package com.blogging.blog.services.imple;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.blog.entities.Category;
import com.blogging.blog.exceptions.ResourceNotFoundException;
import com.blogging.blog.payloads.CategoryDto;
import com.blogging.blog.repositories.CategoryRepo;
import com.blogging.blog.services.CategoryService;

@Service
public class CategoryServiceImple implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category savedCategory = this.categoryRepo.save(this.modelMapper.map(categoryDto, Category.class));
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int catId) {
		Category category = this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id", catId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category savedCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int catId) {
		Category category = this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id", catId));
		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(int catId) {
		Category category = this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id", catId));
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		return this.categoryRepo.findAll().stream().map(category->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
	}

}
