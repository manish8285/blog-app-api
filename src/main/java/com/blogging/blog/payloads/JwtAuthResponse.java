package com.blogging.blog.payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {
	String token;
	UserDto user;
}
