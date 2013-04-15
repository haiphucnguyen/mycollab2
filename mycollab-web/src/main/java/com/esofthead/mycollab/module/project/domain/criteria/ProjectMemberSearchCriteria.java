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
public class ProjectMemberSearchCriteria extends SearchCriteria {

	private NumberSearchField projectId;

	private NumberSearchField id;
	
	private StringSearchField status;

	public NumberSearchField getId() {
		return id;
	}

	public void setId(NumberSearchField id) {
		this.id = id;
	}

	public NumberSearchField getProjectId() {
		return projectId;
	}

	public void setProjectId(NumberSearchField projectId) {
		this.projectId = projectId;
	}

	public void setStatus(StringSearchField status) {
		this.status = status;
	}

	public StringSearchField getStatus() {
		return status;
	}
}
