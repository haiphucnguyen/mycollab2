/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ListView;

/**
 * 
 * @author haiphucnguyen
 */
public interface ProjectRoleListView extends
		ListView<ProjectRoleSearchCriteria, SimpleProjectRole> {
}
