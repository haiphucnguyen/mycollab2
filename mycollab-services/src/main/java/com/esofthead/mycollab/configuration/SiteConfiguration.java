package com.esofthead.mycollab.configuration;

import java.util.Properties;

public class SiteConfiguration {
	private static SiteConfiguration instance;

	static {
		instance = new SiteConfiguration();

		instance.sentErrorEmail = ApplicationProperties.getString(
				ApplicationProperties.ERROR_SENDTO, "hainguyen@esofthead.com");

		instance.siteName = ApplicationProperties.getString(
				ApplicationProperties.SITE_NAME, "MyCollab");

		// load Deployment Mode
		String runningMode = ApplicationProperties.getString(
				ApplicationProperties.RUNNING_MODE, null);
		if ("site".equals(runningMode)) {
			instance.deploymentMode = DeploymentMode.SITE;
		} else {
			instance.deploymentMode = DeploymentMode.LOCAL;
		}

		instance.cdnUrl = ApplicationProperties
				.getString(ApplicationProperties.CDN_URL);

		// load sharing options
		SharingOptions shareOptions = new SharingOptions();
		shareOptions.setFacebookUrl(ApplicationProperties
				.getString(ApplicationProperties.FACEBOOK_URL));
		shareOptions.setTwitterUrl(ApplicationProperties
				.getString(ApplicationProperties.TWITTER_URL));
		shareOptions.setLinkedinUrl(ApplicationProperties
				.getString(ApplicationProperties.LINKEDIN_URL));
		shareOptions.setGoogleplusUrl(ApplicationProperties
				.getString(ApplicationProperties.GOOGLE_URL));

		instance.sharingOptions = shareOptions;

		// Load storage configuration
		String storageSystem = ApplicationProperties.getString(
				ApplicationProperties.STORAGE_SYSTEM, "file");
		if (StorageConfiguration.FILE_STORAGE_SYSTEM.equals(storageSystem)) {
			instance.storageConfiguration = FileStorageConfiguration.build();
		} else {
			instance.storageConfiguration = S3StorageConfiguration
					.build(ApplicationProperties.getAppProperties());
		}

		// load email
		String host = ApplicationProperties
				.getString(ApplicationProperties.MAIL_SMTPHOST);
		String user = ApplicationProperties
				.getString(ApplicationProperties.MAIL_USERNAME);
		String password = ApplicationProperties
				.getString(ApplicationProperties.MAIL_PASSWORD);
		Integer port = Integer.parseInt(ApplicationProperties.getString(
				ApplicationProperties.MAIL_PORT, "-1"));
		Boolean isTls = Boolean.parseBoolean(ApplicationProperties.getString(
				ApplicationProperties.MAIL_IS_TLS, "false"));
		instance.emailConfiguration = new EmailConfiguration(host, user,
				password, port, isTls);

		// load relay email
		host = ApplicationProperties
				.getString(ApplicationProperties.RELAYMAIL_SMTPHOST);
		port = Integer.parseInt(ApplicationProperties.getString(
				ApplicationProperties.RELAYMAIL_PORT, "0"));
		user = ApplicationProperties
				.getString(ApplicationProperties.RELAYMAIL_USERNAME);
		password = ApplicationProperties
				.getString(ApplicationProperties.RELAYMAIL_PASSWORD);
		isTls = Boolean.parseBoolean(ApplicationProperties.getString(
				ApplicationProperties.RELAYMAIL_IS_TLS, "false"));
		instance.relayEmailConfiguration = new EmailConfiguration(host, user,
				password, port, isTls);

		// load database configuration
		String driverClass = ApplicationProperties
				.getString(ApplicationProperties.DB_DRIVER_CLASS);
		String dbUrl = ApplicationProperties
				.getString(ApplicationProperties.DB_URL);
		String dbUser = ApplicationProperties
				.getString(ApplicationProperties.DB_USERNAME);
		String dbPassword = ApplicationProperties
				.getString(ApplicationProperties.DB_PASSWORD);
		instance.databaseConfiguration = new DatabaseConfiguration(driverClass,
				dbUrl, dbUser, dbPassword);

		// load cache properties
		Properties props = new Properties();
		props.put("infinispan.client.hotrod.server_list", ApplicationProperties
				.getString("infinispan.client.hotrod.server_list", ""));
		instance.cacheProperties = props;

		instance.dropboxCallbackUrl = ApplicationProperties
				.getString("dropbox.callbackUrl");
	}

	private DeploymentMode deploymentMode;
	private SharingOptions sharingOptions;
	private StorageConfiguration storageConfiguration;
	private String sentErrorEmail;
	private String siteName;
	private EmailConfiguration emailConfiguration;
	private EmailConfiguration relayEmailConfiguration;
	private DatabaseConfiguration databaseConfiguration;
	private String cdnUrl;
	private Properties cacheProperties;
	private String dropboxCallbackUrl;

	public static Properties getCacheProperties() {
		return instance.cacheProperties;
	}

	public static String getCdnUrl() {
		return instance.cdnUrl;
	}

	public static DatabaseConfiguration getDatabaseConfiguration() {
		return instance.databaseConfiguration;
	}

	public static EmailConfiguration getRelayEmailConfiguration() {
		return instance.relayEmailConfiguration;
	}

	public static EmailConfiguration getEmailConfiguration() {
		return instance.emailConfiguration;
	}

	public static String getSiteName() {
		return instance.siteName;
	}

	public static DeploymentMode getDeploymentMode() {
		return instance.deploymentMode;
	}

	public static String getSendErrorEmail() {
		return instance.sentErrorEmail;
	}

	public static StorageConfiguration getStorageConfiguration() {
		return instance.storageConfiguration;
	}

	public static boolean isSupportFileStorage() {
		return instance.storageConfiguration instanceof FileStorageConfiguration;
	}

	public static boolean isSupportS3Storage() {
		return instance.storageConfiguration instanceof S3StorageConfiguration;
	}

	public static SharingOptions getSharingOptions() {
		return instance.sharingOptions;
	}

	public static String getSiteUrl(String subdomain) {
		String siteUrl = "";
		if (instance.deploymentMode == DeploymentMode.SITE) {
			siteUrl = String.format(ApplicationProperties
					.getString(ApplicationProperties.APP_URL), subdomain);
		} else {
			siteUrl = ApplicationProperties
					.getString(ApplicationProperties.APP_URL);
		}
		return siteUrl;
	}

	public static String getDropboxCallbackUrl(String subdomain) {
		String siteUrl = "";
		if (instance.deploymentMode == DeploymentMode.SITE) {
			siteUrl = String.format(ApplicationProperties
					.getString(ApplicationProperties.APP_URL), subdomain);
		} else {
			siteUrl = ApplicationProperties
					.getString(ApplicationProperties.APP_URL);
		}
		return siteUrl;
	}
}
