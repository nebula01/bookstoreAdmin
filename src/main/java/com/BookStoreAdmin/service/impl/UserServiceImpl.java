package com.BookStoreAdmin.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookStoreAdmin.domain.User;
import com.BookStoreAdmin.domain.security.PasswordResetToken;
import com.BookStoreAdmin.domain.security.UserRole;
import com.BookStoreAdmin.repository.RoleRepository;
import com.BookStoreAdmin.repository.UserRepository;
import com.BookStoreAdmin.service.UserService;

@Service //service로 등록
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	// User
	@Autowired
	private UserRepository userRepository;
	
	// Role 
	@Autowired
	private RoleRepository roleRepository;

	// 새 사용자 생성
	@Override
	public User createUser(User user, Set<UserRole> userRoles) {
		// 우선 등록된 유저인지를 아이디를 통해 찾음
		User localUser = userRepository.findByUsername(user.getUsername());

		if (localUser != null) {
			LOG.info("사용자 {} 는/은 이미 등록되어 있습니다.", user.getUsername());
		} else { // 새 사용자일 시
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}

			user.getUserRoles().addAll(userRoles);

			localUser = userRepository.save(user);
		}

		return localUser;
	}

	// 사용자 DB에 등록
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
}
