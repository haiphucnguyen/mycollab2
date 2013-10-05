package com.esofthead.mycollab.rest.server.domain;

import javax.ws.rs.FormParam;

public class SignupForm {

	@FormParam("subdomain")
	private String subdomain;

	@FormParam("planId")
	private Integer planId;

	@FormParam("password")
	private String password;

	@FormParam("email")
	private String email;

	@FormParam("timezoneId")
	private String timezoneId;

	public String getSubdomain() {
		return subdomain;
	}

	public void setSubdomain(String subdomain) {
		this.subdomain = subdomain;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTimezoneId() {
		return timezoneId;
	}

	public void setTimezoneId(String timezoneId) {
		this.timezoneId = timezoneId;
	}
}
