package com.blogging.blog.payloads;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDto {
	
	private int categoryId;
	
	@NotEmpty
	private String categoryTitle;
	@NotEmpty
	private String categoryDescription;
}
