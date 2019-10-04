package com.singhambar.services.impl;

import java.io.Serializable;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singhambar.app.utilities.AppUtils;
import com.singhambar.app.utilities.HashGeneratorUtils;
import com.singhambar.beans.AuthToken;
import com.singhambar.beans.BeanId;
import com.singhambar.beans.User;
import com.singhambar.repositories.UserRepository;
import com.singhambar.services.AuthTokenService;
import com.singhambar.services.BaseService;
import com.singhambar.services.UserService;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Service("userService")
public class UserServiceImpl<T extends BeanId, ID extends Serializable> extends BaseService<User, Long>
		implements UserService<User, Long> {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthTokenService<AuthToken, Long> authTokenService;

	@Override
	public User findByEmailIdAndPassword(String name, String password) {
		return userRepository.findByEmailIdAndPassword(name, password);
	}

	@Override
	public AuthToken login(String name, String password) throws Exception {
		User user = findByEmailIdAndPassword(name, AppUtils.encrypt(password));

		AuthToken newToken = new AuthToken();

		String token = RandomStringUtils.randomAlphanumeric(16);
		String rawValidator = RandomStringUtils.randomAlphanumeric(64);

		String hashedValidator = HashGeneratorUtils.generateSHA256(rawValidator);

		newToken.setToken(token);
		newToken.setUser(user);
		newToken.setValidator(hashedValidator);
		newToken = authTokenService.update(newToken);
		newToken.setValidationKey(rawValidator);
		return newToken;
	}

	@Override
	public void logout(User user, boolean logoutAll) throws Exception {
		if (logoutAll) {
			// TODO
			authTokenService.deleteAllByUser(user);
		} else {
			authTokenService.deleteEntity(user.getCurrentAuthTokenId());
		}

	}

}
