package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.GroupableSearchCriteria;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class NoteSearchCriteria extends SearchCriteria implements
		GroupableSearchCriteria {
	private StringSearchField type;
	private NumberSearchField typeid;
	private NumberSearchField saccountid;

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

	public NumberSearchField getSaccountid() {
		return saccountid;
	}

	public void setSaccountid(NumberSearchField saccountid) {
		this.saccountid = saccountid;
	}

}
