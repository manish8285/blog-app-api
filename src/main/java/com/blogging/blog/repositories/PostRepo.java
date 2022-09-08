package com.blogging.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogging.blog.entities.Category;
import com.blogging.blog.entities.Post;
import com.blogging.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByCategory(Category category);
	List<Post> findByUser(User user);
	
	List<Post> findByPostTitleContaining(String keyword);
	
	@Query("select p from Post p where p.postTitle like %:key%")
	List<Post> searchBytitle(@Param("key") String keyword);
}
