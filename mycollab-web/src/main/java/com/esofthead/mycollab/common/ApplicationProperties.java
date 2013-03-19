package com.esofthead.mycollab.common;

import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class ApplicationProperties {
	private static final String RESOURCE_PROPERTIES = "resources.properties";
	private static final String DECRYPT_PASS = "esofthead321";

	private static Properties properties;

	public static final String CDN_URL = "cdn.url";

	public static final String APP_URL = "app.url";

	public static final String FACEBOOK_URL = "facebook.url";
	public static final String GOOGLE_URL = "google.url";
	public static final String LINKEDIN_URL = "linkedin.url";
	public static final String TWITTER_URL = "twitter.url";

	public static final String MAIL_SMTPHOST = "mail.smtphost";
	public static final String MAIL_PORT = "mail.port";
	public static final String MAIL_USERNAME = "mail.username";
	public static final String MAIL_PASSWORD = "mail.password";
	public static final String MAIL_IS_TLS = "mail.isTLS";
	public static final String MAIL_SENDTO = "mail.sendTo";

	public static final String RELAYMAIL_SMTPHOST = "relaymail.smtphost";
	public static final String RELAYMAIL_PORT = "relaymail.port";
	public static final String RELAYMAIL_USERNAME = "relaymail.username";
	public static final String RELAYMAIL_PASSWORD = "relaymail.password";
	public static final String RELAYMAIL_IS_TLS = "relaymail.isTLS";

	public static final String ERROR_SENDTO = "error.sendTo";
	public static final String STORAGE_SYSTEM = "storageSystem";

	static {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(DECRYPT_PASS);

		properties = new EncryptableProperties(encryptor);
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

	public static String getSendErrorEmail() {
		return properties.getProperty("error.sendTo", "hainguyen@esofthead.com");
	}
}
