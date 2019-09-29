/**
 * 
 */
package com.singhambar.repositories;

import org.springframework.stereotype.Repository;

import com.singhambar.beans.AuthToken;
import com.singhambar.beans.User;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Repository
public interface AuthTokenRepository extends BaseRepository<AuthToken, Long> {

	public AuthToken findByToken(String token);

	public AuthToken findByTokenAndValidator(String token, String validator);

	public void deleteAllByUser(User user);
}
