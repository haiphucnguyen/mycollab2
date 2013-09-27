/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.View;

/**
 * 
 * @author haiphucnguyen
 */
public interface ProjectMemberListView extends View {
	void setSearchCriteria(ProjectMemberSearchCriteria searchCriteria);
}
