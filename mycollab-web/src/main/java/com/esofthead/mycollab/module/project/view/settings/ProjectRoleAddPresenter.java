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

package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.events.ProjectRoleEvent;
import com.esofthead.mycollab.module.project.service.ProjectRoleService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectRoleAddPresenter extends
		AbstractPresenter<ProjectRoleAddView> {
	private static final long serialVersionUID = 1L;

	public ProjectRoleAddPresenter() {
		super(ProjectRoleAddView.class);
	}

	@Override
	protected void postInitView() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<ProjectRole>() {
					private static final long serialVersionUID = 1L;

					@Override
					public void onSave(final ProjectRole item) {
						save(item);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new ProjectRoleEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new ProjectRoleEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(ProjectRole item) {
						save(item);
						EventBus.getInstance().fireEvent(
								new ProjectRoleEvent.GotoAdd(this, null));
					}
				});
	}

	public void save(ProjectRole item) {
		ProjectRoleService roleService = ApplicationContextUtil
				.getSpringBean(ProjectRoleService.class);
		item.setSaccountid(AppContext.getAccountId());

		SimpleProject project = CurrentProjectVariables.getProject();
		item.setProjectid(project.getId());

		if (item.getId() == null) {
			roleService.saveWithSession(item, AppContext.getUsername());
		} else {
			roleService.updateWithSession(item, AppContext.getUsername());
		}

		roleService.savePermission(project.getId(), item.getId(),
				view.getPermissionMap(), AppContext.getAccountId());

	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectRoleContainer roleContainer = (ProjectRoleContainer) container;
		roleContainer.removeAllComponents();
		roleContainer.addComponent(view.getWidget());

		ProjectRole role = (ProjectRole) data.getParams();

		view.editItem(role);
		ProjectBreadcrumb breadcrumb = ViewManager
				.getView(ProjectBreadcrumb.class);
		if (role.getId() == null) {
			breadcrumb.gotoRoleAdd();
		} else {
			breadcrumb.gotoRoleEdit((SimpleProjectRole) role);
		}
	}
}
