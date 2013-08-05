package com.esofthead.mycollab.common;

import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class ApplicationProperties {
	private static final String RESOURCE_PROPERTIES = "resources.properties";
	private static final String DECRYPT_PASS = "esofthead321";

	private static Properties properties;

	public static final String DB_USERNAME = "db.username";
	public static final String DB_PASSWORD = "db.password";
	public static final String DB_DRIVER_CLASS = "db.driverClassName";
	public static final String DB_URL = "db.url";

	public static final String CDN_URL = "cdn.url";

	private static final String APP_URL = "app.url";

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

	public static final String SITE_NAME = "site.name";
	private static final String RUNNING_MODE = "running.mode";
	public static final String SUPPORT_ACCOUNT_SUBDOMAIN = "isSupportAccountSubDomain";

	public static final String DROPBOX_AUTH_LINK = "";
	public static final String GOOGLE_DRIVE_LINK = "";

	public static boolean productionMode = false;

	static {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(DECRYPT_PASS);

		properties = new EncryptableProperties(encryptor);
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(RESOURCE_PROPERTIES));

			productionMode = "production".equals(properties
					.getProperty(RUNNING_MODE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Properties getAppProperties() {
		return properties;
	}

	public static String getString(String key) {
		return properties.getProperty(key);
	}

	public static String getString(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(properties.getProperty(key,
				Boolean.FALSE.toString()));
	}

	public static String getSendErrorEmail() {
		return properties
				.getProperty("error.sendTo", "hainguyen@esofthead.com");
	}

	public static String getSiteUrl(String subdomain) {
		String siteUrl = "";
		if (ApplicationProperties.productionMode) {
			siteUrl = String.format(ApplicationProperties
					.getString(ApplicationProperties.APP_URL), subdomain);
		} else {
			boolean isSupportSubDomain = ApplicationProperties
					.getBoolean(ApplicationProperties.SUPPORT_ACCOUNT_SUBDOMAIN);
			if (isSupportSubDomain) {
				siteUrl = String.format(ApplicationProperties
						.getString(ApplicationProperties.APP_URL), subdomain);
			} else {
				siteUrl = ApplicationProperties
						.getString(ApplicationProperties.APP_URL);
			}
		}
		return siteUrl;
	}
}
