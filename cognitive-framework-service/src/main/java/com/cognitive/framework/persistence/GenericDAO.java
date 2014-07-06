package com.cognitive.framework.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.cognitive.framework.servicelocator.ServiceLocator;
import com.cognitive.framework.util.SystemConfiguration;

public abstract class GenericDAO <E, I> {

	private Class<E> clazz;

	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		if (getClass().getSuperclass().equals(GenericDAO.class)) {
			this.clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} else {
			this.clazz = (Class<E>) ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		//(Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@PostConstruct
	private void setup() {
		String entityManagerResourceName = SystemConfiguration.get("ENTITY_MANAGER_RESOURCE_NAME");
		this.entityManager = ServiceLocator.getResource(entityManagerResourceName, EntityManager.class);
	}
	
	public E find(I id) {
		return entityManager.find(clazz, id);
	}
	
	public E insert(E entity) {
		
		this.entityManager.persist(entity);
		return entity;
	}
	
	public E update(E entidade) {

		return entityManager.merge(entidade);
	}
	
	public void delete(I id) {
		entityManager.remove(find(id));
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		Query query = entityManager.createQuery(
				// new way for NoSQL
				"from " + clazz.getSimpleName() + " e order by e.id desc"
				);
		//"select e from " + clazz.getSimpleName() + " e order by e.id desc" old way for relational databases
		
		return query.getResultList();    
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
}
