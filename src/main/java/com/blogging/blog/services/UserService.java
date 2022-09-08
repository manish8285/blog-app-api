package com.blogging.blog.services;

import java.util.List;

import com.blogging.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	UserDto registerNewRegister(UserDto user);
	
	UserDto updateUser(UserDto user,int userId);
	UserDto getUserById(int userId);
	List<UserDto> getAllUsers();
	void deleteUser(int userId);
}
