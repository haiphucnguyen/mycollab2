/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.security.BooleanPermissionFlag;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewPermission;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
@ViewPermission(permissionId = RolePermissionCollections.CREATE_NEW_PROJECT, impliedPermissionVal = BooleanPermissionFlag.TRUE)
public class ProjectAddPresenter extends AbstractPresenter<ProjectAddView> {
	private static final long serialVersionUID = 1L;

	public ProjectAddPresenter() {
		super(ProjectAddView.class);
		bind();
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ComponentContainer projectContainer = (ComponentContainer) container;
		projectContainer.removeAllComponents();
		projectContainer.addComponent(view.getWidget());
		Project project = (Project) data.getParams();
		view.editItem(project);

		if (project.getId() == null) {
			AppContext.addFragment("project/add", "New Project");
		}
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Project>() {
					@Override
					public void onSave(final Project project) {
						saveProject(project);
						EventBus.getInstance().fireEvent(
								new ProjectEvent.GotoMyProject(this,
										new PageActionChain(
												new ProjectScreenData.Goto(view
														.getItem().getId()))));
					}

					@Override
					public void onCancel() {
						if (view.getItem().getId() == null) {
							EventBus.getInstance().fireEvent(
									new ShellEvent.GotoProjectModule(
											ProjectAddPresenter.this, null));
						} else {
							EventBus.getInstance()
									.fireEvent(
											new ProjectEvent.GotoMyProject(
													this,
													new PageActionChain(
															new ProjectScreenData.Goto(
																	view.getItem()
																			.getId()))));
						}
					}

					@Override
					public void onSaveAndNew(final Project project) {

					}
				});
	}

	public void saveProject(Project project) {
		ProjectService projectService = ApplicationContextUtil
				.getSpringBean(ProjectService.class);
		project.setSaccountid(AppContext.getAccountId());

		if (project.getId() == null) {
			projectService.saveWithSession(project, AppContext.getUsername());
		} else {
			projectService.updateWithSession(project, AppContext.getUsername());
			AppContext.putVariable(ProjectContants.CURRENT_PROJECT, project);
		}

	}
}
