/**
 * 
 */
package com.singhambar.beans;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
@MappedSuperclass
public class BeanId {

	@Id()
	@NotNull
	@Column(name = "ID", updatable = false, unique = true)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@PrePersist
	public void generateId() {
		id = (id == null) ? System.currentTimeMillis() : id;
	}
}
