package com.esofthead.mycollab.module.user.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class RoleSearchCriteria {
	private StringSearchField username;
	
	private NumberSearchField accountid;

	public StringSearchField getUsername() {
		return username;
	}

	public void setUsername(StringSearchField username) {
		this.username = username;
	}

	public NumberSearchField getAccountid() {
		return accountid;
	}

	public void setAccountid(NumberSearchField accountid) {
		this.accountid = accountid;
	}
}
