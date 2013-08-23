/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.events.ProjectRoleEvent;
import com.esofthead.mycollab.module.project.service.ProjectRoleService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectRoleAddPresenter extends
		AbstractPresenter<ProjectRoleAddView> {
	private static final long serialVersionUID = 1L;

	public ProjectRoleAddPresenter() {
		super(ProjectRoleAddView.class);

		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<ProjectRole>() {
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
		ProjectRoleService roleService = AppContext
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
