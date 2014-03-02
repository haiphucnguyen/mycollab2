package com.esofthead.mycollab.core.db.query;

import com.esofthead.mycollab.core.arguments.SearchField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class CompositionStringParam extends Param {
	private StringParam[] params;
	private String[] compareOptions;

	public CompositionStringParam(String id, String displayName,
			StringParam[] params) {
		this.id = id;
		this.displayName = displayName;
		this.params = params;
	}

	public StringParam[] getParams() {
		return params;
	}

	public void setParams(StringParam[] params) {
		this.params = params;
	}

	public String[] getCompareOptions() {
		return compareOptions;
	}

	public void setCompareOptions(String[] compareOptions) {
		this.compareOptions = compareOptions;
	}

	public SearchField buildSearchField() {
		return null;
	}
}
