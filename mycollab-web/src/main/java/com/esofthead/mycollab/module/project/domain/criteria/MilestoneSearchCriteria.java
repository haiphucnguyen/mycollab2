/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

/**
 *
 * @author haiphucnguyen
 */
public class MilestoneSearchCriteria extends SearchCriteria{
    private BooleanSearchField completedField;
    
    private StringSearchField assignUser;
    
    private StringSearchField flag;
    
    private NumberSearchField projectId;
    
    private NumberSearchField id;

    public BooleanSearchField getCompletedField() {
        return completedField;
    }

    public void setCompletedField(BooleanSearchField completedField) {
        this.completedField = completedField;
    }

    public StringSearchField getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(StringSearchField assignUser) {
        this.assignUser = assignUser;
    }

    public StringSearchField getFlag() {
        return flag;
    }

    public void setFlag(StringSearchField flag) {
        this.flag = flag;
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
