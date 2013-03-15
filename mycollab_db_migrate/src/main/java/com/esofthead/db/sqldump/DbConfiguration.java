package com.esofthead.db.sqldump;

import java.util.Properties;

public class DbConfiguration {
	public static final String USER_NAME = "db.username";
	public static final String PASSWORD = "db.password";
	public static final String URL = "db.url";

	public static final String H2_DRIVER = "h2.driverClassName";
	public static final String H2_URL = "h2.url";

	private static final String RESOURCE_PROPERTIES = "config.properties";
	private static Properties properties;
	static {
		properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(RESOURCE_PROPERTIES));
		} catch (Exception e) {
			properties = null;
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		if (null != properties)
			return properties.getProperty(key);
		return null;
	}

	public static DbConfiguration loadDefault() {
		return null;
	}
}
