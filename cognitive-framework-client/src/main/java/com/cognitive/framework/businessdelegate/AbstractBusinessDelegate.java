package com.cognitive.framework.businessdelegate;

import java.lang.reflect.ParameterizedType;

import com.cognitive.framework.servicelocator.ServiceLocator;

public abstract class AbstractBusinessDelegate <T> {

	private String environment;
	
	private String applicationName;
	
	private String moduleName;
	
	private String serviceName;
	
	private Class<T> serviceInterface;
	
	@SuppressWarnings("unchecked")
	protected void setup(String environment, String applicationName, String moduleName, String serviceName) {
		
		this.environment = environment;
		this.applicationName = applicationName;
		this.moduleName = moduleName;
		this.serviceName = serviceName;
		this.serviceInterface = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public T getService() {
		return ServiceLocator.getService(
				this.serviceInterface, 
				this.serviceName, 
				this.moduleName, 
				this.environment, 
				this.applicationName, 
				this.serviceInterface.getName());
	}
}
