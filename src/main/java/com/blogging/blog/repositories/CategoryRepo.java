package com.blogging.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
