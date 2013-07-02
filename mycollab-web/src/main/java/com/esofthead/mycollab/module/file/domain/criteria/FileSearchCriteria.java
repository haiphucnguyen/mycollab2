package com.esofthead.mycollab.module.file.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class FileSearchCriteria extends SearchCriteria {
	private StringSearchField fileName;
	private NumberSearchField id;

	public StringSearchField getFileName() {
		return this.fileName;
	}

	public void setFileName(final StringSearchField fileName) {
		this.fileName = fileName;
	}

	public void setId(final NumberSearchField id) {
		this.id = id;
	}

	public NumberSearchField getId() {
		return this.id;
	}
}
