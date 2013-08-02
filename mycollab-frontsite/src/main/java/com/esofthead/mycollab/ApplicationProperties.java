package com.esofthead.mycollab;

import java.util.Properties;

public class ApplicationProperties {
	private static final String RESOURCE_PROPERTIES = "settings.properties";
	private static Properties properties;

	public static final String SIGNUP_URL = "signup.url";

	static {
		properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(RESOURCE_PROPERTIES));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Properties getAppProperties() {
		return properties;
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}
}
