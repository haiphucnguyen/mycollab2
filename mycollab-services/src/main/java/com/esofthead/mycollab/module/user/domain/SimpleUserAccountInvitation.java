package com.esofthead.mycollab.module.user.domain;

public class SimpleUserAccountInvitation extends UserAccountInvitation {
	private static final long serialVersionUID = 1L;

	private String subdomain;
	
	private String inviterFullName;

	public String getSubdomain() {
		return subdomain;
	}

	public void setSubdomain(String subdomain) {
		this.subdomain = subdomain;
	}

	public String getInviterFullName() {
		return inviterFullName;
	}

	public void setInviterFullName(String inviterFullName) {
		this.inviterFullName = inviterFullName;
	}
}
