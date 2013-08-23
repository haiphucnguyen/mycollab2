package com.esofthead.mycollab.common.domain.criteria;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class SaveSearchResultCriteria extends SearchCriteria {

	private StringSearchField type;

	private StringSearchField createUser;

	public StringSearchField getType() {
		return type;
	}

	public void setType(StringSearchField type) {
		this.type = type;
	}

	public StringSearchField getCreateUser() {
		return createUser;
	}

	public void setCreateUser(StringSearchField createUser) {
		this.createUser = createUser;
	}
}
