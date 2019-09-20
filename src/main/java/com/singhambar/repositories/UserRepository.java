package com.singhambar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.singhambar.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User createUser(User user) throws Exception;

	User updateUser(User user) throws Exception;

	User deleteUser(Long userId) throws Exception;

	User getUser(Long userId) throws Exception;

	User getUsers() throws Exception;
	
	public List<User> findByNameContainingIgnoreCase(String searchString);
}
