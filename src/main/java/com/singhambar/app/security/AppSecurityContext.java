/**
 * 
 */
package com.singhambar.app.security;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import com.singhambar.beans.User;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public class AppSecurityContext implements SecurityContext {
	private User user;
	private String protocol;

	public AppSecurityContext(User user) {
		this.user = user;
	}

	public AppSecurityContext(User user, String protocol) {
		this.user = user;
		this.protocol = protocol;
	}

	@Override
	public Principal getUserPrincipal() {
		return this.user;
	}

	@Override
	public boolean isUserInRole(String role) {
		if (user.getRole() != null) {
			return user.getRole().contains(role);
		}
		return false;
	}

	@Override
	public boolean isSecure() {
		return "https".equals(this.protocol);
	}

	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}
}
