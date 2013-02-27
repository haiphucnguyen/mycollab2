package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class ProjectRoleSearchCriteria extends SearchCriteria {

    private StringSearchField rolename;
    private NumberSearchField projectId;
    private NumberSearchField saccountid;
    private NumberSearchField id;

    public StringSearchField getRolename() {
        return rolename;
    }

    public void setRolename(StringSearchField rolename) {
        this.rolename = rolename;
    }

    public NumberSearchField getSaccountid() {
        return saccountid;
    }

    public void setSaccountid(NumberSearchField saccountid) {
        this.saccountid = saccountid;
    }

	public NumberSearchField getProjectId() {
		return projectId;
	}

	public void setProjectId(NumberSearchField projectId) {
		this.projectId = projectId;
	}

	public void setId(NumberSearchField id) {
		this.id = id;
	}

	public NumberSearchField getId() {
		return id;
	}
}
