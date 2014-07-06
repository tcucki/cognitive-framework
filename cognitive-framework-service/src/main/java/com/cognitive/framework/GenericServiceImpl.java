package com.cognitive.framework;

import java.util.ArrayList;
import java.util.List;

import com.cognitive.framework.model.EntityModel;
import com.cognitive.framework.persistence.GenericDAO;
import com.cognitive.framework.service.CrudService;
import com.cognitive.framework.translator.AbstractTranslator;
import com.cognitive.framework.vo.ValueObject;

public class GenericServiceImpl<V extends ValueObject<I>, E extends EntityModel, I> implements CrudService<V, I> {
	
	private GenericDAO<E, I> genericDAO;
	
	private AbstractTranslator<I, V, E> translator;

	@Override
	public V find(I id) {
		E entity = this.getGenericDAO().find(id);
		return this.getTranslator().translateEntityToVO(entity);
	}
/*
	@Override
	public V insert(V valueObject) {
		E entity = this.getTranslator().translateVoToEntity(valueObject);
		entity = this.getGenericDAO().insert(entity);
		return this.getTranslator().translateEntityToVO(entity);
	}

	@Override
	public V update(V valueObject) {
		E entity = this.getTranslator().translateVoToEntity(valueObject);
		entity = this.getGenericDAO().update(entity);
		return this.getTranslator().translateEntityToVO(entity);
	}
*/
	@Override
	public V save(V valueObject) {
		E entity = this.getTranslator().translateVoToEntity(valueObject);
		I id = valueObject.getId();
		if (id == null || "".equals(id.toString())) {
			entity = this.getGenericDAO().insert(entity);
		} else {
			entity = this.getGenericDAO().update(entity);
		}
		return this.getTranslator().translateEntityToVO(entity);
	};
	
	@Override
	public void delete(I id) {
		this.getGenericDAO().delete(id);
	}

	@Override
	public List<V> findAll() {
		List<E> entities = this.getGenericDAO().findAll();
		List<V> valueObjects = new ArrayList<>(entities.size());
		for (E entity : entities) {
			V valueObject = this.getTranslator().translateEntityToVO(entity);
			valueObjects.add(valueObject);
		}
		return valueObjects;
	}

	protected GenericDAO<E, I> getGenericDAO() {
		return genericDAO;
	}

	protected void setGenericDAO(GenericDAO<E, I> genericDAO) {
		this.genericDAO = genericDAO;
	}

	protected AbstractTranslator<I, V, E> getTranslator() {
		return translator;
	}

	protected void setTranslator(AbstractTranslator<I, V, E> translator) {
		this.translator = translator;
	}

}
