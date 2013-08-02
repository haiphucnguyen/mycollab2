/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectGenericTaskSearchCriteria extends SearchCriteria {
    public static final String OPEN_STATUS = "Open";
    
    public static final String CLOSE_STATUS = "Closed";
    
    private NumberSearchField sAccountId;
    
    private NumberSearchField projectId;
    
    private StringSearchField assignUser;
    
    private SearchField isOpenned;

    public NumberSearchField getsAccountId() {
        return sAccountId;
    }

    public void setsAccountId(NumberSearchField sAccountId) {
        this.sAccountId = sAccountId;
    }

    public NumberSearchField getProjectId() {
        return projectId;
    }

    public void setProjectId(NumberSearchField projectId) {
        this.projectId = projectId;
    }

    public StringSearchField getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(StringSearchField assignUser) {
        this.assignUser = assignUser;
    }

	public SearchField getIsOpenned() {
		return isOpenned;
	}

	public void setIsOpenned(SearchField isOpenned) {
		this.isOpenned = isOpenned;
	}
}
