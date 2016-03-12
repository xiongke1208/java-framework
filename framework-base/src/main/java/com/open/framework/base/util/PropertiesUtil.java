package com.open.framework.base.util;

import java.util.Properties;

public class PropertiesUtil {

	private static Properties peroperties = null;
	
	/**
	 * @param key
	 * @param defaultValue
	 */
	public static String getPropertyByKey(String key, String defaultValue) {
		return peroperties.getProperty(key, defaultValue);
	}
	
	static {
		peroperties = (Properties)
				ApplicationContextHolder.getBean("settings");
	}
	
}
