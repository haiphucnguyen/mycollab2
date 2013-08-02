package com.esofthead.mycollab.module.user.domain;

public class SimpleUserAccountInvitation extends UserAccountInvitation {
	private static final long serialVersionUID = 1L;

	private String subdomain;

	public String getSubdomain() {
		return subdomain;
	}

	public void setSubdomain(String subdomain) {
		this.subdomain = subdomain;
	}
}
