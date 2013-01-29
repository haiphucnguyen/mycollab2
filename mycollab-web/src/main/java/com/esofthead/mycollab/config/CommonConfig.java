package com.esofthead.mycollab.config;

import java.util.Properties;

import org.jasypt.util.text.BasicTextEncryptor;

public class CommonConfig {
	private static final String CLASS_NAME = "db.driverClassName";
	private static final String URL = "db.url";
	private static final String USER_NAME = "db.username";
	private static final String PASSWORD = "db.password";
	private static final String CONFIG_FILE = "resources.properties";

	private static final String DECRYPT_PASS = "esofthead321";
	
	private String className;
	private String url;
	private String userName;
	private String password;

	public String getClassName() {
		return className;
	}

	public String getUrl() {
		return url;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	private CommonConfig(String className, String url, String userName,
			String password) {
		this.className = className;
		this.url = url;
		this.userName = userName;
		this.password = password;
	}
	
	public static final CommonConfig loadConfig() {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(CONFIG_FILE));
			String className = properties.getProperty(CLASS_NAME);
			String url = properties.getProperty(URL);
			String userName = properties.getProperty(USER_NAME);
			String password = properties.getProperty(PASSWORD);
			
			BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
			textEncryptor.setPassword(DECRYPT_PASS);
			String decryptPass = textEncryptor.decrypt(password);
					
			return new CommonConfig(className, url, userName, decryptPass);
		} catch (Exception e) {
		}
		return null;
	}
}
