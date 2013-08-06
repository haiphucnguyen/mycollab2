package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class MessageSearchCriteria extends SearchCriteria {
	private NumberSearchField id;
	
    private SetSearchField<Integer> projectids;
    
    private StringSearchField category;
    
    private StringSearchField title;
    
    private StringSearchField message;

	public NumberSearchField getId() {
		return id;
	}

	public void setId(NumberSearchField id) {
		this.id = id;
	}

	public SetSearchField<Integer> getProjectids() {
		return projectids;
	}

	public void setProjectids(SetSearchField<Integer> projectids) {
		this.projectids = projectids;
	}

	public StringSearchField getCategory() {
		return category;
	}

	public void setCategory(StringSearchField category) {
		this.category = category;
	}

	public void setTitle(StringSearchField title) {
		this.title = title;
	}

	public StringSearchField getTitle() {
		return title;
	}

	public void setMessage(StringSearchField message) {
		this.message = message;
	}

	public StringSearchField getMessage() {
		return message;
	}
}
