package com.BookStoreAdmin.repository;

import org.springframework.data.repository.CrudRepository;

import com.BookStoreAdmin.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	// 아이디으로 사용자 찾기
	User findByUsername(String username);
	
}
