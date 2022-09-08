package com.blogging.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
	
}
