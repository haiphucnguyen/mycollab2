package com.esofthead.mycollab;

import java.util.Properties;

public class SiteConfiguration {
	private static final String RESOURCE_PROPERTIES = "settings.properties";

	private static SiteConfiguration appInstance;

	public static final String API_URL = "api.url";

	private String apiUrl;

	private String appUrl;

	static {
		appInstance = new SiteConfiguration();

		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(RESOURCE_PROPERTIES));
			appInstance.apiUrl = properties.getProperty(API_URL);
			appInstance.appUrl = properties.getProperty("app.url");
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

	public static String getSigninUrl() {
		return appInstance.apiUrl + "/signin/";
	}

	public static String getSignupUrl() {
		return appInstance.apiUrl + "/signup/";
	}

	public static String getErrorReportingUrl() {
		return appInstance.apiUrl + "/errorReport/";
	}
}
