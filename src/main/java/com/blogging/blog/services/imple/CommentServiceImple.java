package com.blogging.blog.services.imple;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.blog.entities.Comment;
import com.blogging.blog.entities.Post;
import com.blogging.blog.entities.User;
import com.blogging.blog.exceptions.ResourceNotFoundException;
import com.blogging.blog.payloads.CommentDto;
import com.blogging.blog.repositories.CommentRepo;
import com.blogging.blog.repositories.PostRepo;
import com.blogging.blog.repositories.UserRepo;
import com.blogging.blog.services.CommentService;



@Service
public class CommentServiceImple implements CommentService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId, int userId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user id", userId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setDate(new Date());
		comment.setPost(post);
		comment.setUser(user);
		Comment comment2 = this.commentRepo.save(comment);
		return this.modelMapper.map(comment2, CommentDto.class);
		
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment id", commentId));
		this.commentRepo.delete(comment);
	}

}
