package com.singhambar.beans;

import java.io.Serializable;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Entity
@Table(name = "USER")
public class User extends BeanId implements Principal, Serializable {

	private static final long serialVersionUID = -5494612033115021308L;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 48)
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 48)
	@Column(name = "LAST_NAME")
	private String lastName;

	@NotNull
	@NotBlank
	@Email
	@Size(min = 5, max = 512)
	@Column(name = "EMAIL_ID", unique = true)
	private String emailId;

	@Size(max = 15)
	@Column(name = "CONTACT_NO")
	private String contactNo;

	@NotNull
	@Size(min = 8, max = 512)
	@Column(name = "PASSWORD")
	private String password;

	@NotNull
	@NotBlank
	@Size(max = 20)
	@Column(name = "ROLE")
	private String role;

	@NotNull
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@NotNull
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<AuthToken> tokens;

	private transient Long currentAuthTokenId = null;

	private transient String name = null;

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
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo
	 *            the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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
	 * @param modifiedDate
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
	 * @param currentAuthTokenId
	 *            the currentAuthTokenId to set
	 */
	public void setCurrentAuthTokenId(Long currentAuthTokenId) {
		this.currentAuthTokenId = currentAuthTokenId;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = firstName + " " + lastName;
	}

	@Transient
	@Override
	public String getName() {
		return name;
	}
}
