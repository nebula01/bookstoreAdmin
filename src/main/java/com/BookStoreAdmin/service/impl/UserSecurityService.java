package com.BookStoreAdmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.BookStoreAdmin.domain.User;
import com.BookStoreAdmin.repository.UserRepository;

@Component
public class UserSecurityService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("찾으려는 아이디가 없습니다");
		}
		
		return user;
	}
}
