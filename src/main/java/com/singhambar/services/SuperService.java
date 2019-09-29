/**
 * 
 */
package com.singhambar.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.singhambar.beans.BeanId;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public interface SuperService<T extends BeanId, ID> {

	@Transactional
	T createEntity(T instance) throws Exception;

	@Transactional
	T createEntity(T instance, boolean flush) throws Exception;

	@Transactional
	T updateEntity(T instance) throws Exception;

	@Transactional
	T getEntity(ID id) throws Exception;

	@Transactional
	List<T> getEntities() throws Exception;

	@Transactional
	void deleteEntity(ID id) throws Exception;

	@Transactional
	void deleteEntity(T instance) throws Exception;
	
	@Transactional
	void deleteAll() throws Exception;

	@Transactional
	void deleteEntitiesInBatch(List<T> instances) throws Exception;

	@Transactional
	Boolean exists(ID id) throws Exception;

}
