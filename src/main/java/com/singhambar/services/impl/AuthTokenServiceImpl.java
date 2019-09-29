/**
 * 
 */
package com.singhambar.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singhambar.beans.AuthToken;
import com.singhambar.beans.BeanId;
import com.singhambar.beans.User;
import com.singhambar.repositories.AuthTokenRepository;
import com.singhambar.services.AuthTokenService;
import com.singhambar.services.BaseService;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Service("authTokenService")
public class AuthTokenServiceImpl<T extends BeanId, ID extends Serializable> extends BaseService<AuthToken, Long>
		implements AuthTokenService<AuthToken, Long> {

	@Autowired
	AuthTokenRepository authTokenRepository;

	@Override
	public AuthToken findByTokenAndValidator(String token, String validator) {
		return authTokenRepository.findByToken(token);
	}

	@Override
	public AuthToken update(AuthToken token) {
		return authTokenRepository.save(token);
	}

	@Override
	public void deleteAllByUser(User user) {
		authTokenRepository.deleteAllByUser(user);
	}

}
