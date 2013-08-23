package com.esofthead.mycollab.module.user.domain.criteria;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class RoleSearchCriteria extends SearchCriteria {

	private StringSearchField username;
	private StringSearchField roleName;

	public StringSearchField getUsername() {
		return username;
	}

	public void setUsername(StringSearchField username) {
		this.username = username;
	}

	public void setRoleName(StringSearchField roleName) {
		this.roleName = roleName;
	}

	public StringSearchField getRoleName() {
		return roleName;
	}

}
