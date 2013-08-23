package com.esofthead.mycollab.configuration;

public class DatabaseConfiguration {

	private String driverClass;

	private String dbUrl;

	private String user;

	private String password;

	DatabaseConfiguration(String driverClass, String dbUrl, String user,
			String password) {
		this.user = user;
		this.driverClass = driverClass;
		this.dbUrl = dbUrl;
		this.password = password;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
}
