package com.cognitive.framework.servicelocator;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;

import com.cognitive.framework.exception.CognitiveRuntimeException;

public class ServiceLocator {

	private static ServiceLocator instance = null;
	
	private Context context;
	
	public static <T> T getService(
			Class<T> service, String serviceName, String moduleName, 
			String environment, String application, String interfaceName) {
		
		return getInstance().lookupService(service, serviceName, moduleName, environment, application, interfaceName);
	}
	
	public static <T> T getResource(String resourceName, Class<T> clazz) {
		
		return getInstance().lookupResource(resourceName, clazz);
	}

	private static ServiceLocator getInstance() {
		if (instance == null) {
			instance = new ServiceLocator();
		}
		return instance;
	}
	
	private ServiceLocator() {
		
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		try {
			this.context = new InitialContext(jndiProperties);
		} catch (NamingException e) {
			throw new CognitiveRuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T> T lookupService(
			Class<T> service, String serviceName, String moduleName, 
			String environment, String application, String interfaceName) {
		
		String name = environment + "/";
		
		if (StringUtils.isNotBlank(application)) {
			name = name + application + "/";
		}
		name = name + moduleName + "/" + serviceName;
		if (StringUtils.isNotBlank(interfaceName)) {
			name = name + "!" + interfaceName;
		}
		try {
			return (T) this.context.lookup(name);
		} catch (NamingException e) {
			throw new CognitiveRuntimeException(e); 
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T> T lookupResource(String resourceName, Class<T> cazz) {

		try {
			return (T) this.context.lookup(resourceName);
		} catch (NamingException e) {
			throw new CognitiveRuntimeException(e); 
		}
	}
}
