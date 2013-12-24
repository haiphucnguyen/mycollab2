package com.esofthead.mycollab;

import java.util.Properties;

import com.esofthead.mycollab.core.DeploymentMode;

public class SiteConfiguration {
	private static final String RESOURCE_PROPERTIES = "settings.properties";

	private static SiteConfiguration appInstance;

	public static final String API_URL = "api.url";

	private String apiUrl;

	private String appUrl;

	private String siteUrl;

    private String chatUrl;

	private DeploymentMode deploymentMode;

	static {
		appInstance = new SiteConfiguration();

		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(RESOURCE_PROPERTIES));
			appInstance.apiUrl = properties.getProperty(API_URL);
			appInstance.appUrl = properties.getProperty("app.url");
			appInstance.siteUrl = properties.getProperty("site.url");
            appInstance.chatUrl = properties.getProperty("chat.url");

			// load Deployment Mode
			String runningMode = properties.getProperty("running.mode");
			if ("site".equals(runningMode)) {
				appInstance.deploymentMode = DeploymentMode.SITE;
			} else {
				appInstance.deploymentMode = DeploymentMode.LOCAL;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getApiUrl() {
		return appInstance.apiUrl;
	}

	public static String getAppUrl() {
		return appInstance.appUrl;
	}

	public static String getSiteUrl() {
		return appInstance.siteUrl;
	}

    public static String getChatUrl() {
        return appInstance.chatUrl;
    }

	public static DeploymentMode getDeploymentMode() {
		return appInstance.deploymentMode;
	}
}
