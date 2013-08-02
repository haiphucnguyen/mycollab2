/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.tracker.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

/**
 *
 * @author haiphucnguyen
 */
public class VersionSearchCriteria extends SearchCriteria {
    private NumberSearchField projectId;
    
    private NumberSearchField id;
    
    private StringSearchField versionname;

    public NumberSearchField getProjectId() {
        return projectId;
    }

    public void setProjectId(NumberSearchField projectId) {
        this.projectId = projectId;
    }

    public NumberSearchField getId() {
        return id;
    }

    public void setId(NumberSearchField id) {
        this.id = id;
    }

    public StringSearchField getVersionname() {
        return versionname;
    }

    public void setVersionname(StringSearchField versionname) {
        this.versionname = versionname;
    }
}
