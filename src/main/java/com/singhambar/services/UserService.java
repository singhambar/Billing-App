package com.singhambar.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.singhambar.beans.AuthToken;
import com.singhambar.beans.User;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public interface UserService {

	User createUser(User user) throws Exception;

	User updateUser(User user) throws Exception;

	void deleteUser(Long userId) throws Exception;

	User getUser(Long userId) throws Exception;

	List<User> getUsers() throws Exception;

	User findByEmailIdAndPassword(String name, String password);
	
	@Transactional
	AuthToken login(String name, String password) throws Exception;
}
