package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class ResourceSearchCriteria extends SearchCriteria {
    private StringSearchField resourcename;
    
    private NumberSearchField projectId;
    
    private BooleanSearchField assignedToProject;

	public StringSearchField getResourcename() {
		return resourcename;
	}

	public void setResourcename(StringSearchField resourcename) {
		this.resourcename = resourcename;
	}

	public NumberSearchField getProjectId() {
		return projectId;
	}

	public void setProjectId(NumberSearchField projectId) {
		this.projectId = projectId;
	}

	public BooleanSearchField getAssignedToProject() {
		return assignedToProject;
	}

	public void setAssignedToProject(BooleanSearchField assignedToProject) {
		this.assignedToProject = assignedToProject;
	}
}
