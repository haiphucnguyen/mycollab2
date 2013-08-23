package com.esofthead.mycollab.configuration;

public class EmailConfiguration {
	private String host;
	private String user;
	private String password;
	private int port;
	private boolean isTls;

	EmailConfiguration(String host, String username, String password, int port,
			boolean isTLS) {
		this.host = host;
		this.user = username;
		this.password = password;
		this.port = port;
		this.isTls = isTLS;
	}

	public String getHost() {
		return host;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public int getPort() {
		return port;
	}

	public boolean isTls() {
		return isTls;
	}

}
