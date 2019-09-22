package com.singhambar.services.impl;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.singhambar.app.HashGeneratorUtils;
import com.singhambar.app.security.EncrypterAndDecrypter;
import com.singhambar.beans.AuthToken;
import com.singhambar.beans.User;
import com.singhambar.repositories.AuthTokenRepository;
import com.singhambar.repositories.UserRepository;
import com.singhambar.services.UserService;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthTokenRepository authTokenRepository;

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
		User user = findByEmailIdAndPassword(name,enc.encrypt(password));

		AuthToken newToken= new AuthToken();
		
		String token = RandomStringUtils.randomAlphanumeric(12);
		String rawValidator =  RandomStringUtils.randomAlphanumeric(64);
		 
		String hashedValidator = HashGeneratorUtils.generateSHA256(rawValidator);
		 
		newToken.setToken(token);
		newToken.setUserId(user);
		newToken.setValidator(hashedValidator);
		newToken=authTokenRepository.save(newToken);
		newToken.setValidationKey(rawValidator);
		return newToken;
	}

}
