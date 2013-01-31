package com.esofthead.mycollab.module.mail.service;

import java.util.Properties;

public class CampaignConfig {
	private static final String API_KEY = "API_KEY";
	private static final String FROM_TEMPLATE = "FROM_TEMPLATE";

	private static final String USER_NAME = "USER_NAME";
	private static final String PASSWORD = "PASSWORD";
	private static final String MAIL_FROM = "MAIL_FROM";
	private static final String MAIL_TO = "MAIL_TO";

	private static final String CONFIG_FILE = "config.properties";

	private String apiKey;
	private String senderMail;

	private String userName;
	private String password;
	private String mailFrom;
	private String mailTo;

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getMailForm() {
		return mailFrom;
	}

	public String getMailTo() {
		return mailTo;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getSenderMail() {
		return senderMail;
	}

	private CampaignConfig(String apiKey, String senderMail, String userName,
			String password, String mailFrom, String mailTo) {
		this.apiKey = apiKey;
		this.senderMail = senderMail;
		this.userName = userName;
		this.password = password;
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
	}

	public static final CampaignConfig loadConfig() {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(CONFIG_FILE));
			String apiKey = properties.getProperty(API_KEY);
			String senderMail = properties.getProperty(FROM_TEMPLATE);

			String userName = properties.getProperty(USER_NAME);
			String password = properties.getProperty(PASSWORD);
			String mailFrom = properties.getProperty(MAIL_FROM);
			String mailTo = properties.getProperty(MAIL_TO);

			return new CampaignConfig(apiKey, senderMail, userName, password,
					mailFrom, mailTo);
		} catch (Exception e) {
			return null;
		}
	}
}
