/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

/**
 *
 * @author haiphucnguyen
 */
public class TaskListSearchCriteria extends SearchCriteria {

    private NumberSearchField projectId;
    
    private StringSearchField status;

    public NumberSearchField getProjectId() {
        return projectId;
    }

    public void setProjectId(NumberSearchField projectId) {
        this.projectId = projectId;
    }

    public StringSearchField getStatus() {
        return status;
    }

    public void setStatus(StringSearchField status) {
        this.status = status;
    }
}
