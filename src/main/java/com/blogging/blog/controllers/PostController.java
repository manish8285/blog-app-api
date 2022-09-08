package com.blogging.blog.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogging.blog.config.AppConstants;
import com.blogging.blog.payloads.ApiResponse;
import com.blogging.blog.payloads.PostDto;
import com.blogging.blog.payloads.PostResponse;
import com.blogging.blog.services.FileService;
import com.blogging.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postdto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		PostDto createdPost = this.postService.createPost(postdto, categoryId, userId);
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
	}
	
	//get post by user
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> postDtos = this.postService.getPostByUser(userId);
		return new ResponseEntity<>(postDtos,HttpStatus.OK);
	}
	
	// get posts by category
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> postDtos = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<>(postDtos,HttpStatus.OK);
	}
	
	//get all posts
		
	@GetMapping("posts")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy){
		PostResponse postResponse = this.postService.getposts(pageNumber,pageSize,sortBy);
		return ResponseEntity.ok(postResponse);
	}
	
	//get posts by id
	@GetMapping("posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto post = this.postService.getPost(postId);
		return ResponseEntity.ok(post);
	}
	
	// delete post
	
	@DeleteMapping("posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post has been deleted successfully",true),HttpStatus.OK);
	}
	
	// update post
	@PutMapping("posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return ResponseEntity.ok(updatedPost);
	}
	
	@GetMapping("posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword) {
		List<PostDto> postDtos = this.postService.searchPost(keyword);
		return ResponseEntity.ok(postDtos);
	}
	
	// upload image
	@PostMapping("post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@PathVariable Integer postId,
			@RequestParam("image") MultipartFile image) throws IOException{
		PostDto postDto = this.postService.getPost(postId);
		String uploadedImageName = this.fileService.uploadImage(path, image);
		
		postDto.setPostImageName(uploadedImageName);
		PostDto updatedPostDto = this.postService.updatePost(postDto, postId);
		return ResponseEntity.ok(updatedPostDto);
	}
	
	// method to serve image
	@GetMapping(value="image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException {
		InputStream stream = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(stream, response.getOutputStream());
	}
}
