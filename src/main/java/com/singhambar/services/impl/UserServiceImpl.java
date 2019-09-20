package com.singhambar.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.singhambar.beans.User;
import com.singhambar.repositories.UserRepository;
import com.singhambar.services.UserService;

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
		return userRepository.updateUser(user);
	}

	@Transactional
	public User deleteUser(Long userId) throws Exception {
		return userRepository.deleteUser(userId);
	}

	public User getUser(Long userId) throws Exception {
		return userRepository.getUser(userId);
	}

	public User getUsers() throws Exception {
		return userRepository.getUsers();
	}

}
