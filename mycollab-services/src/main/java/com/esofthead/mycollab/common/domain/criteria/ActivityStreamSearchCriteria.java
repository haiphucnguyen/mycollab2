package com.esofthead.mycollab.common.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class ActivityStreamSearchCriteria extends SearchCriteria {

	private SetSearchField<String> moduleSet;
	private SetSearchField<Integer> extraTypeIds;
	private StringSearchField createdUser;

	public StringSearchField getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(StringSearchField createdUser) {
		this.createdUser = createdUser;
	}

	public SetSearchField<String> getModuleSet() {
		return moduleSet;
	}

	public void setModuleSet(SetSearchField<String> moduleSet) {
		this.moduleSet = moduleSet;
	}

	public SetSearchField<Integer> getExtraTypeIds() {
		return extraTypeIds;
	}

	public void setExtraTypeIds(SetSearchField<Integer> extraTypeIds) {
		this.extraTypeIds = extraTypeIds;
	}
}
