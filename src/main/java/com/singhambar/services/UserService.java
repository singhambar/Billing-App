package com.singhambar.services;

import java.io.Serializable;

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

	User findByEmailIdAndPassword(String name, String password);

	@Transactional
	AuthToken login(String name, String password) throws Exception;

	@Transactional
	void logout(User user, boolean logoutAll) throws Exception;
}
