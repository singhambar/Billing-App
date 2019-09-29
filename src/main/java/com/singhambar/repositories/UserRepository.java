package com.singhambar.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.singhambar.beans.User;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

	public User findByEmailIdAndPassword(String emailId, String password);

	public List<User> findByFirstNameContainingIgnoreCase(String searchString);
}
