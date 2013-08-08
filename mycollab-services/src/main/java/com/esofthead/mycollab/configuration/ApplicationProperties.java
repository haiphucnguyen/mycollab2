package com.esofthead.mycollab.configuration;

import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

class ApplicationProperties {
	private static final String RESOURCE_PROPERTIES = "resources.properties";
	private static final String DECRYPT_PASS = "esofthead321";

	private static Properties properties;

	public static final String DB_USERNAME = "db.username";
	public static final String DB_PASSWORD = "db.password";
	public static final String DB_DRIVER_CLASS = "db.driverClassName";
	public static final String DB_URL = "db.url";

	public static final String CDN_URL = "cdn.url";

	public static final String APP_URL = "app.url";

	static final String FACEBOOK_URL = "facebook.url";
	static final String GOOGLE_URL = "google.url";
	static final String LINKEDIN_URL = "linkedin.url";
	static final String TWITTER_URL = "twitter.url";

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

	public static final String SITE_NAME = "site.name";
	public static final String RUNNING_MODE = "running.mode";

	public static final String DROPBOX_AUTH_LINK = "";
	public static final String GOOGLE_DRIVE_LINK = "";

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

	public static String getString(String key) {
		return properties.getProperty(key, "");
	}

	public static String getString(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}
}
