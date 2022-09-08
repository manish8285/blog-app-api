package com.blogging.blog.services;

import java.util.List;

import com.blogging.blog.payloads.PostDto;
import com.blogging.blog.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer categoryId,Integer userId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get
	PostDto getPost(Integer postId);
	
	//get all post
	PostResponse getposts(int pageNumber,int pageSize,String sortBy);
	
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getPostByUser(Integer userId);
	
	//Search post
	List<PostDto> searchPost(String keyword);
	
}
