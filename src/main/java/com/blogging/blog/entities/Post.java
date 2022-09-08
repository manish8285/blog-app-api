package com.blogging.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	private String postTitle;
	@Column(length = 10000)
	private String postContent;
	private String postImageName;
	private Date postAddedDate;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy = "post")
	private Set<Comment> comments = new HashSet<>();

}
