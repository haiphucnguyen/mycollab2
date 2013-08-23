package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class NoteSearchCriteria extends SearchCriteria {
	private StringSearchField type;
	private NumberSearchField typeid;

	public StringSearchField getType() {
		return type;
	}

	public void setType(StringSearchField type) {
		this.type = type;
	}

	public NumberSearchField getTypeid() {
		return typeid;
	}

	public void setTypeid(NumberSearchField typeid) {
		this.typeid = typeid;
	}

}
