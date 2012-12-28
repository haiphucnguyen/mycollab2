/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;

/**
 *
 * @author haiphucnguyen
 */
public class TaskListSearchCriteria extends SearchCriteria{
    private NumberSearchField projectId;

    public NumberSearchField getProjectId() {
        return projectId;
    }

    public void setProjectId(NumberSearchField projectId) {
        this.projectId = projectId;
    }
    
}
