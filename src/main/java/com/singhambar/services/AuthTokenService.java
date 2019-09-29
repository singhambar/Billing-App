/**
 * 
 */
package com.singhambar.services;

import java.io.Serializable;

import com.singhambar.beans.AuthToken;
import com.singhambar.beans.BeanId;
import com.singhambar.beans.User;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public interface AuthTokenService<T extends BeanId, ID extends Serializable> extends SuperService<T, ID> {

	public AuthToken findByTokenAndValidator(String token, String validator);

	public AuthToken update(AuthToken token);

	public void deleteAllByUser(User user);
}
