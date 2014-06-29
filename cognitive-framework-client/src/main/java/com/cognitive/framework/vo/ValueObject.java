package com.cognitive.framework.vo;

import java.io.Serializable;

public class ValueObject <I>  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1702731363508824919L;

	private I id;

	public I getId() {
		return id;
	}

	public void setId(I id) {
		this.id = id;
	}
}
