package com.blogging.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private int postId;
	
	@NotEmpty
	private String postTitle;
	@NotEmpty
	private String postContent;
	private String postImageName;

	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments= new HashSet<>();
}
