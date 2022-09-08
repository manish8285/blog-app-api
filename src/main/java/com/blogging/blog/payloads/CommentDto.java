package com.blogging.blog.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	private int id;
	private Date date;
	private String content;
	private UserDto user;
	
}
