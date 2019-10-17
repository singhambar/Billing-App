/**
 * 
 */
package com.singhambar.beans;

import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */

@Entity
@Table(name = "AUTH_TOKEN")
public class AuthToken extends BeanId implements Serializable {

	private static final long serialVersionUID = 8215969076699490277L;

	@NotNull
	@Column(name = "TOKEN")
	private String token;

	@NotNull
	@Column(name = "VALIDATOR")
	private String validator;

	@NotNull
	@JoinColumn(name = "USER_ID")
	@ManyToOne(fetch = EAGER)
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
