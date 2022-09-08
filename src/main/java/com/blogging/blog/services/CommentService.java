package com.blogging.blog.services;

import com.blogging.blog.payloads.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto,int postId, int userId);
	void deleteComment(int commentId);
}
