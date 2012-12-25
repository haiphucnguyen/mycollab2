package com.esofthead.mycollab.common.domain.criteria;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;

public class ActivityStreamSearchCriteria extends SearchCriteria {
	private SetSearchField<String> moduleSet;

	public SetSearchField<String> getModuleSet() {
		return moduleSet;
	}

	public void setModuleSet(SetSearchField<String> moduleSet) {
		this.moduleSet = moduleSet;
	}
}
