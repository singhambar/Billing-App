/**
 * 
 */
package com.singhambar.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */

@Entity
@Table(name = "AUTH_TOKEN")
public class AuthToken extends BeanId {

	@Column(name = "TOKEN")
	private String token;

	@Column(name = "VALIDATOR")
	private String validator;

	@JoinColumn(name = "USER_ID", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	private transient String validationKey;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getValidator() {
		return validator;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getValidationKey() {
		return validationKey;
	}

	public void setValidationKey(String validationKey) {
		this.validationKey = validationKey;
	}

}
