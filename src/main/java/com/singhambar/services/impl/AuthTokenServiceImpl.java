/**
 * 
 */
package com.singhambar.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singhambar.beans.AuthToken;
import com.singhambar.repositories.AuthTokenRepository;
import com.singhambar.services.AuthTokenService;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Service("authTokenService")
public class AuthTokenServiceImpl implements AuthTokenService{

	@Autowired
	AuthTokenRepository authTokenRepository;
	
	@Override
	public AuthToken findByTokenAndValidator(String token, String validator) {
		
		authTokenRepository.findAll();
		
		return authTokenRepository.findByToken(token);
	}

	@Override
	public AuthToken update(AuthToken token) {
		return authTokenRepository.save(token);
	}

}
