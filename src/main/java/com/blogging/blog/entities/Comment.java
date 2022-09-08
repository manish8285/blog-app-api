package com.blogging.blog.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;
	private String content;
	private Date date;
	
	@ManyToOne
	private User user;
	@ManyToOne
	private Post post;
}
