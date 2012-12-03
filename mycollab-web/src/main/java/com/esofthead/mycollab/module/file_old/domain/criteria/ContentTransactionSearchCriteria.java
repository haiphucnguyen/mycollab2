package com.esofthead.mycollab.module.file_old.domain.criteria;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class ContentTransactionSearchCriteria extends SearchCriteria {
    
    private StringSearchField user;
    
    private StringSearchField pathStartFrom;

	public StringSearchField getUser() {
		return user;
	}

	public void setUser(StringSearchField user) {
		this.user = user;
	}

	public StringSearchField getPathStartFrom() {
		return pathStartFrom;
	}

	public void setPathStartFrom(StringSearchField pathStartFrom) {
		this.pathStartFrom = pathStartFrom;
	}
}
