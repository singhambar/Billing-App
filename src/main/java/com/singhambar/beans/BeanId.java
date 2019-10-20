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
		id = (id == null||id<0) ? System.currentTimeMillis() : id;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ":" + getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof BeanId) {
			BeanId b = (BeanId) obj;
			return b.getId() == getId();
		} else {
			return super.equals(obj);
		}
	}

	@Override
	public int hashCode() {
		return this.getId().intValue();

	}
}
