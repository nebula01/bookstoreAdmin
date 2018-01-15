package com.BookStoreAdmin.service;

import java.util.Set;

import com.BookStoreAdmin.domain.User;
import com.BookStoreAdmin.domain.security.PasswordResetToken;
import com.BookStoreAdmin.domain.security.UserRole;

public interface UserService {
	
	/*// 비밀번호 다시 받을 때 token리셋
	PasswordResetToken getPasswordResetToken(final String token);

	// 비밀번호 token생성
	void createPasswordResetTokenForUser(final User user, final String token);

	// 아이디로 유저 찾기
	User findByUsername(String username);

	// 이메일로 유저 찾기
	User findByEmail(String email);
*/
	// 새 사용자 생성
	User createUser(User user, Set<UserRole> userRoles) throws Exception;

	// 사용자 등록
	User save(User user);
}
