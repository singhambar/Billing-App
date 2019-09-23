/**
 * 
 */
package com.singhambar.services;

import com.singhambar.beans.AuthToken;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public interface AuthTokenService {

	
	public AuthToken findByTokenAndValidator(String token, String validator);
	
	public AuthToken update(AuthToken token);
}
