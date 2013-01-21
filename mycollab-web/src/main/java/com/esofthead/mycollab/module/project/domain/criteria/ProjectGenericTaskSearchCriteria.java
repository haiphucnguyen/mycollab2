/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectGenericTaskSearchCriteria extends SearchCriteria {
    private NumberSearchField sAccountId;
    
    private NumberSearchField projectId;
    
    private SetSearchField<String> statuses;

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

    public SetSearchField<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(SetSearchField<String> statuses) {
        this.statuses = statuses;
    }
}
