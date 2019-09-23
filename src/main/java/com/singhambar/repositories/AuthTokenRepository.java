/**
 * 
 */
package com.singhambar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.singhambar.beans.AuthToken;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long>{

	public AuthToken findByToken(String token);
	
	public AuthToken findByTokenAndValidator(String token, String validator);
}
