package com.cognitive.framework.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.cognitive.framework.servicelocator.ServiceLocator;

public abstract class GenericDAO <E, I> {

	private Class<E> clazz;

//	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		// TODO Auto-generated constructor stub
		if (getClass().getSuperclass().equals(GenericDAO.class)) {
			this.clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} else {
			this.clazz = (Class<E>) ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		//(Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@PostConstruct
	private void setup() {
		this.entityManager = ServiceLocator.getResource("java:jboss/exported/persistence/newsreader-persistence-unit", EntityManager.class);
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
				"select e from " + clazz.getSimpleName() + " e order by e.identificador desc");
		
		return query.getResultList();    
	}
	
}
