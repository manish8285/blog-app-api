package com.blogging.blog.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.blog.exceptions.ApiException;
import com.blogging.blog.payloads.JwtAuthRequest;
import com.blogging.blog.payloads.JwtAuthResponse;
import com.blogging.blog.payloads.UserDto;
import com.blogging.blog.security.JwtTokenHelper;
import com.blogging.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("login")
	ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest request) throws Exception{
		
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse authResponse = new JwtAuthResponse();
		authResponse.setToken(token);
		authResponse.setUser(this.modelMapper.map(userDetails, UserDto.class));
		return new ResponseEntity<JwtAuthResponse>(authResponse,HttpStatus.OK);
		
	}
	
	@PostMapping("signup")
	ResponseEntity<UserDto> registerNewUser(
			@Valid @RequestBody UserDto userDto){
		UserDto createdUser = this.userService.registerNewRegister(userDto);
		return new ResponseEntity<>(createdUser,HttpStatus.OK);
		
	}
	

	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		try {
		this.authenticationManager.authenticate(authenticationToken);
		}catch(BadCredentialsException e) {
			throw new ApiException("Invalid username or password");
		}
		
	}
	
}
