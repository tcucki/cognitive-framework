package com.cognitive.framework.businessdelegate;

public enum EnvironmentType {

	GLOBAL("java:global"),
	APPLICATION("java:app"),
	MODULE("java:module"),
	JBOSS("java:jboss/exported");
	
	private String value;
	
	private EnvironmentType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
