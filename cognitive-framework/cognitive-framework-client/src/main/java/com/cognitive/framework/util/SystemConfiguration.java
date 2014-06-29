package com.cognitive.framework.util;

import java.util.ResourceBundle;

public class SystemConfiguration {

	private ResourceBundle systemConfiguration = null;
	
	private static SystemConfiguration instance = null;
	
	public static String get(String key) {
		return getInstance().getValue(key);
	}
	
	private static SystemConfiguration getInstance() {
		if (instance == null) {
			instance = new SystemConfiguration();
		}
		return instance;
	}

	private SystemConfiguration() {

		this.systemConfiguration = ResourceBundle.getBundle("system-config");
	}
	
	private String getValue(String key) {
		return this.systemConfiguration.getString(key);
	}
}
