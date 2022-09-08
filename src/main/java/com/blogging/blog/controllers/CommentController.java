package com.blogging.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.blog.payloads.ApiResponse;
import com.blogging.blog.payloads.CommentDto;
import com.blogging.blog.payloads.PostDto;
import com.blogging.blog.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@DeleteMapping("post/comment/{commentId}")
	ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment has been deleted successfully !!!",true),HttpStatus.OK);
	}
	
	@PostMapping("post/{postId}/comment/user/{userId}")
	ResponseEntity<CommentDto> createComment(@PathVariable Integer postId,
			@PathVariable Integer userId,
			@RequestBody CommentDto commentDto){
		CommentDto commentDto2 = this.commentService.createComment(commentDto, postId, userId);
		
		return new ResponseEntity<CommentDto>(commentDto2,HttpStatus.CREATED);
	}
}
