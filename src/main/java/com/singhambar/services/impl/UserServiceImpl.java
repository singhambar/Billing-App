package com.singhambar.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.singhambar.beans.User;
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

}
