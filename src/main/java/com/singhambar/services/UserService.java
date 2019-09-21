package com.singhambar.services;

import java.util.List;

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
}
