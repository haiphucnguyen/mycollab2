package com.esofthead.mycollab.common.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class MonitorSearchCriteria extends SearchCriteria {

	private StringSearchField user;

	private NumberSearchField typeId;

	private StringSearchField type;

	private SetSearchField<Integer> extraTypeIds;

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

	public SetSearchField<Integer> getExtraTypeIds() {
		return extraTypeIds;
	}

	public void setExtraTypeIds(SetSearchField<Integer> extraTypeIds) {
		this.extraTypeIds = extraTypeIds;
	}
}
