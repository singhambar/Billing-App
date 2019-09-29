/**
 * 
 */
package com.singhambar.app.mixins;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.singhambar.beans.AuthToken;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public abstract class UserMixin {

	@JsonIgnore
	public abstract String getPassword();
	
	@JsonIgnore
	public abstract Set<AuthToken> getTokens();
}
