/**
 * 
 */
package com.singhambar.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */

@Entity
@Table(name = "AUTH_TOKEN")
public class AuthToken {

	@Id
	@Column(name = "ID", updatable = false, unique = true, nullable = false)
	private Long id;

	@Column(name = "TOKEN")
	private String token;
	
	@Column(name = "VALIDATOR")
	private String validator;
	
	@JoinColumn(name = "USER_ID", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private User userId;
	
	private transient String validationKey;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
	@PrePersist
	public void generateId() {
		id = (id==null)?System.currentTimeMillis():id;
	}

	public String getValidationKey() {
		return validationKey;
	}

	public void setValidationKey(String validationKey) {
		this.validationKey = validationKey;
	}
	
}
