package com.cognitive.framework.service;

import java.util.List;

import com.cognitive.framework.vo.ValueObject;

public interface CrudService <V extends ValueObject<I>, I> {

	V find(I id);
	
	/*
	V insert(V valueObject);
	
	V update(V valueObject);
	*/
	
	V save(V valueObject);
	
	void delete(I id);
	
	List<V> findAll();
}
