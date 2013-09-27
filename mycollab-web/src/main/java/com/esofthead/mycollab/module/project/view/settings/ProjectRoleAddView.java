/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

/**
 * 
 * @author haiphucnguyen
 */
public interface ProjectRoleAddView extends IFormAddView<ProjectRole> {

	HasEditFormHandlers<ProjectRole> getEditFormHandlers();

	PermissionMap getPermissionMap();
}
