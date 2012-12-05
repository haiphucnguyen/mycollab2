package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class EventSearchCriteria extends SearchCriteria {
	private NumberSearchField saccountid;

	private StringSearchField subject;

	private StringSearchField relatedTo;

	public NumberSearchField getSaccountid() {
		return saccountid;
	}

	public void setSaccountid(NumberSearchField saccountid) {
		this.saccountid = saccountid;
	}

	public StringSearchField getSubject() {
		return subject;
	}

	public void setSubject(StringSearchField subject) {
		this.subject = subject;
	}

	public StringSearchField getRelatedTo() {
		return relatedTo;
	}

	public void setRelatedTo(StringSearchField relatedTo) {
		this.relatedTo = relatedTo;
	}
}
