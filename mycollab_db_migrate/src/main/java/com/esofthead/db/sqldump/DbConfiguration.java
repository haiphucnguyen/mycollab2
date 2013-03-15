package com.esofthead.db.sqldump;

import java.util.Properties;


public class DbConfiguration {
	public static final String USER_NAME = "db.username";
	public static final String PASSWORD = "db.password";
	public static final String URL = "db.url";
	public static final String SCHEMA = "db.schema";

	public static final String H2_DRIVER = "h2.driverClassName";
	public static final String H2_URL = "h2.url";

	private static final String RESOURCE_PROPERTIES = "config.properties";
//	private static Properties properties;
//	static {
//		properties = new Properties();
//		try {
//			properties.load(Thread.currentThread().getContextClassLoader()
//					.getResourceAsStream(RESOURCE_PROPERTIES));
//		} catch (Exception e) {
//			properties = null;
//			e.printStackTrace();
//		}
//	}

//	public static String getProperty(String key) {
//		if (null != properties)
//			return properties.getProperty(key);
//		return null;
//	}
	
	private String userName;
	private String password;
	private String url;
	private String schema;

	public static DbConfiguration loadDefault() {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(RESOURCE_PROPERTIES));
			
			DbConfiguration config = new DbConfiguration();
			
			config.setPassword(properties.getProperty(PASSWORD));
			config.setUserName(properties.getProperty(USER_NAME));
			config.setUrl(properties.getProperty(URL));
			config.setSchema(properties.getProperty(SCHEMA));
			
			return config;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the schema
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @param schema the schema to set
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}
}
