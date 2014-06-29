package com.cognitive.framework.service;

import java.util.List;

public interface CrudService <V, I> {

	V find(I id);
	
	V insert(V valueObject);
	
	V update(V valueObject);
	
	void delete(I id);
	
	List<V> findAll();
}
