package com.singhambar.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.singhambar.beans.AuthToken;
import com.singhambar.beans.BeanId;
import com.singhambar.beans.User;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public interface UserService<T extends BeanId, ID extends Serializable> extends SuperService<T, ID> {

	User createUser(User user) throws Exception;

	User updateUser(User user) throws Exception;

	void deleteUser(Long userId) throws Exception;

	User getUser(Long userId) throws Exception;

	List<User> getUsers() throws Exception;

	User findByEmailIdAndPassword(String name, String password);

	@Transactional
	AuthToken login(String name, String password) throws Exception;

	@Transactional
	void logout(User user, boolean logoutAll) throws Exception;
}
