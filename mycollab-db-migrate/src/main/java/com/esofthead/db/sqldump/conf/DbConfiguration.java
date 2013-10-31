package com.esofthead.db.sqldump.conf;

import java.util.Properties;

public class DbConfiguration {
	private static final String SCHEMA_ONLY_OPTION = "schema_only";
	private static final String DATA_ONLY_OPTION = "data_only";

	private static final String SCHEMA_DATA_OPTION = "schema_data";
	private static final String __SCHEMA_NO_CONSTRAINT = "schema_no_constraint";
	private static final String __SCHEMA_DATA_NO_CONSTAINT = "schema_data_no_constraint";

	public static final String USER_NAME = "db.username";
	public static final String PASSWORD = "db.password";
	public static final String URL = "db.url";
	public static final String EXPORT_OPTION = "db.exportoption";

	public static final int SCHEMA_ONLY = 0;
	public static final int DATA_ONLY = 1;
	public static final int SCHEMA_DATA = 2;
	public static final int SCHEMA_NO_CONSTRAINT = 4;
	public static final int SCHEMA_DATA_NO_CONSTAINT = 8;

	private static final String RESOURCE_PROPERTIES = "config.properties";

	private String userName;
	private String password;
	private String url;
	private boolean isMySqlModel = true;
	private int exportOption = SCHEMA_DATA;

	public static DbConfiguration loadDefault() {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(RESOURCE_PROPERTIES));

			DbConfiguration config = new DbConfiguration();

			config.setPassword(properties.getProperty(PASSWORD));
			config.setUserName(properties.getProperty(USER_NAME));
			config.setUrl(properties.getProperty(URL));
			config.setMySqlModel(null != config.getUrl()
					&& config.getUrl().toLowerCase().startsWith("jdbc:mysql"));

			String exportOption = properties.getProperty(EXPORT_OPTION);
			if (null == exportOption) {
				config.setExportOption(SCHEMA_DATA);
			} else {
				if (exportOption.toLowerCase().equals(SCHEMA_ONLY_OPTION)) {
					config.setExportOption(SCHEMA_ONLY);
				} else if (exportOption.toLowerCase().equals(DATA_ONLY_OPTION)) {
					config.setExportOption(DATA_ONLY);
				} else if (exportOption.toLowerCase()
						.equals(SCHEMA_DATA_OPTION)) {
					config.setExportOption(SCHEMA_DATA);
				} else if (exportOption.toLowerCase().equals(
						__SCHEMA_NO_CONSTRAINT)) {
					config.setExportOption(SCHEMA_NO_CONSTRAINT);
				} else if (exportOption.toLowerCase().equals(
						__SCHEMA_DATA_NO_CONSTAINT)) {
					config.setExportOption(SCHEMA_DATA_NO_CONSTAINT);
				} else {
					config.setExportOption(SCHEMA_DATA);
				}
			}

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
	 * @param userName
	 *            the userName to set
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
	 * @param password
	 *            the password to set
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
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the isMySqlModel
	 */
	public boolean isMySqlModel() {
		return isMySqlModel;
	}

	/**
	 * @param isMySQL
	 *            the isMySQL to set
	 */
	public void setMySqlModel(boolean isMySqlModel) {
		this.isMySqlModel = isMySqlModel;
	}

	/**
	 * @return the exportOption
	 */
	public int getExportOption() {
		return exportOption;
	}

	/**
	 * @param exportOption
	 *            the exportOption to set
	 */
	public void setExportOption(int exportOption) {
		this.exportOption = exportOption;
	}
}
