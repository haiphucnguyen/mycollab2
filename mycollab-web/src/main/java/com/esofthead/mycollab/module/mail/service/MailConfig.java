package com.esofthead.mycollab.module.mail.service;

import java.io.IOException;
import java.util.Properties;

public class MailConfig {
	public static final String API_KEY = "API_KEY";
	public static final String FROM_TEMPLATE = "FROM_TEMPLATE";

	public static final String USER_NAME = "USER_NAME";
	public static final String PASSWORD = "PASSWORD";
	public static final String MAIL_FROM = "MAIL_FROM";
	public static final String MAIL_TO = "MAIL_TO";

	private static final String CONFIG_FILE = "mailcampaign.properties";
	
	private static Properties properties = new Properties();
	
	static {
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(CONFIG_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
