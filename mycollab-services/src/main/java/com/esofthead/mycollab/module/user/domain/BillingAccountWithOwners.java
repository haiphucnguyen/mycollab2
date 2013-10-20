package com.esofthead.mycollab.module.user.domain;

import java.util.List;

public class BillingAccountWithOwners extends SimpleBillingAccount {
	private static final long serialVersionUID = 1L;
	private List<User> owners;

	public List<User> getOwners() {
		return owners;
	}

	public void setOwners(List<User> owners) {
		this.owners = owners;
	}
}
