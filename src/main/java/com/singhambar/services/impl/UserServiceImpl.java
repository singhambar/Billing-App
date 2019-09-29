package com.singhambar.services.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.singhambar.app.security.EncrypterAndDecrypter;
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
 * @param <T>
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

	@Transactional
	public User createUser(User user) throws Exception {
		return userRepository.save(user);
	}

	@Transactional
	public User updateUser(User user) throws Exception {
		return userRepository.save(user);
	}

	@Transactional
	public void deleteUser(Long userId) throws Exception {
		userRepository.deleteById(userId);
	}

	public User getUser(Long userId) throws Exception {
		return userRepository.findById(userId).get();
	}

	public List<User> getUsers() throws Exception {
		return userRepository.findAll();
	}

	@Override
	public User findByEmailIdAndPassword(String name, String password) {
		return userRepository.findByEmailIdAndPassword(name, password);
	}

	@Override
	public AuthToken login(String name, String password) throws Exception {
		EncrypterAndDecrypter enc = new EncrypterAndDecrypter();
		User user = findByEmailIdAndPassword(name, enc.encrypt(password));

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
