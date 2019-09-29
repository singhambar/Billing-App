package com.singhambar.beans;

import java.security.Principal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Entity
@Table(name = "USER")
public class User extends BeanId implements Principal {

	@Column(name = "FIRST_NAME", nullable = false, length = 48)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false, length = 48)
	private String lastName;

	@Column(name = "EMAIL_ID", unique = true, nullable = false, length = 255)
	private String emailId;

	@Column(name = "MOBILE", unique = true, nullable = false, length = 15)
	private String mobile;

	@Column(name = "PASSWORD", nullable = false, length = 255)
	private String password;

	@Column(name = "ROLE", unique = true, nullable = false, length = 20)
	private String role;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade=CascadeType.REMOVE)
	private Set<AuthToken> tokens;
	
	private transient Long currentAuthTokenId=null;

	@Override
	public String getName() {
		return firstName + " " + lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param updationDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the tokens
	 */
	public Set<AuthToken> getTokens() {
		return tokens;
	}

	/**
	 * @param tokens
	 *            the tokens to set
	 */
	public void setTokens(Set<AuthToken> tokens) {
		this.tokens = tokens;
	}

	/**
	 * @return the currentAuthTokenId
	 */
	public Long getCurrentAuthTokenId() {
		return currentAuthTokenId;
	}

	/**
	 * @param currentAuthTokenId the currentAuthTokenId to set
	 */
	public void setCurrentAuthTokenId(Long currentAuthTokenId) {
		this.currentAuthTokenId = currentAuthTokenId;
	}

}
