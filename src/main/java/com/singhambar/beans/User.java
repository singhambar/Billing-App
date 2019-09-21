package com.singhambar.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;


/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@Entity
@Table(name = "USER")
public class User {
	@Id
	@Column(name = "ID", updatable = false, unique = true, nullable = false)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "PASSWORD")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@PrePersist
	public void generateId() {
		id = (id==null)?System.currentTimeMillis():id;
	}

}
