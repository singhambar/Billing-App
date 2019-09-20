package com.singhambar.services;

import com.singhambar.beans.User;

public interface UserService {

	User createUser(User user) throws Exception;

	User updateUser(User user) throws Exception;

	User deleteUser(Long userId) throws Exception;

	User getUser(Long userId) throws Exception;

	User getUsers() throws Exception;
}
