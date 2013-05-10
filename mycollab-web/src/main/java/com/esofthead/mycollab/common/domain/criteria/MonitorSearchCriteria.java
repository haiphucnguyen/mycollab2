package com.esofthead.mycollab.common.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class MonitorSearchCriteria extends SearchCriteria {
	
	private StringSearchField user;
	
	private NumberSearchField typeId;
	
	private StringSearchField type;

	public StringSearchField getUser() {
		return user;
	}

	public void setUser(StringSearchField user) {
		this.user = user;
	}

	public NumberSearchField getTypeId() {
		return typeId;
	}

	public void setTypeId(NumberSearchField typeId) {
		this.typeId = typeId;
	}

	public StringSearchField getType() {
		return type;
	}

	public void setType(StringSearchField type) {
		this.type = type;
	}


}
