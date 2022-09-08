package com.blogging.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
	
}
