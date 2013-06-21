package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class FileSearchCriteria extends SearchCriteria {
	private NumberSearchField projectId;
	private StringSearchField fileName;
	private NumberSearchField id;

	public StringSearchField getFileName() {
		return this.fileName;
	}

	public void setFileName(final StringSearchField fileName) {
		this.fileName = fileName;
	}

	public NumberSearchField getProjectId() {
		return this.projectId;
	}

	public void setProjectId(final NumberSearchField projectId) {
		this.projectId = projectId;
	}

	public void setId(final NumberSearchField id) {
		this.id = id;
	}

	public NumberSearchField getId() {
		return this.id;
	}
}
