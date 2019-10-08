/**
 * 
 */
package com.singhambar.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.singhambar.beans.BeanId;
import com.singhambar.repositories.BaseRepository;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public abstract class BaseService<T extends BeanId, ID extends Serializable> implements SuperService<T, ID> {

	private BaseRepository<T, Long> baseRepository;

	/**
	 * @param baseRepository
	 *            the baseRepository to set
	 */
	@Autowired
	public void setBaseRepository(BaseRepository<T, Long> baseRepository) {
		this.baseRepository = baseRepository;
	}

	public T createEntity(T instance, boolean flush) {
		if (instance.getId()!=null && instance.getId() < 0) {
			instance.setId(null);
		}
		T instanceCreated = flush ? baseRepository.saveAndFlush(instance) : baseRepository.save(instance);
		return instanceCreated;
	}

	public T createEntity(T instance) {
		return createEntity(instance, false);
	}

	public T updateEntity(T instance) throws Exception {
		Long id = instance.getId();
		if (id == null || !baseRepository.existsById(id))
			throw new Exception("Entity does not exist.");
		T instanceUpdated = baseRepository.save(instance);
		return instanceUpdated;
	}

	public T getEntity(ID id) {
		return baseRepository.findById((Long) id).orElse(null);
	}

	public List<T> getEntities() {
		return baseRepository.findAll();
	}

	public Page<T> getEntities(Pageable pageable) {
		return baseRepository.findAll(pageable);
	}

	public void deleteEntity(T instance) {
		baseRepository.delete(instance);
	}

	public void deleteEntity(ID id) {
		baseRepository.deleteById((Long) id);
	}

	public void deleteAll(T instance) {
		baseRepository.deleteAll();
	}

	public void deleteEntitiesInBatch(List<T> instances) {
		baseRepository.deleteInBatch(instances);
	}

	public void deleteAll() {
		baseRepository.deleteAll();
	}

	public Boolean exists(ID id) throws Exception {
		return baseRepository.existsById((Long) id);
	}

}
