package com.singhambar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.singhambar.beans.User;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByName(String searchString);

	public List<User> findByNameContainingIgnoreCase(String searchString);
}
