package com.singhambar.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(name = "USER")
public class User {
	@Id
	@Column(name = "ID", updatable = false, unique = true, nullable = false)
	private Long id;

	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name = "NAME")
	private String name;

	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name = "EMAIL_ID")
	private String emailId;

	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
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
