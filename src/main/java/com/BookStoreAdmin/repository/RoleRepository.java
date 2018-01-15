package com.BookStoreAdmin.repository;

import org.springframework.data.repository.CrudRepository;

import com.BookStoreAdmin.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	// 이름으로 Role찾기
	Role findByname(String name);
}
