/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectRoleEvent;
import com.esofthead.mycollab.module.project.service.ProjectRoleService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectRoleReadPresenter extends
		AbstractPresenter<ProjectRoleReadView> {
	private static final long serialVersionUID = 1L;

	public ProjectRoleReadPresenter() {
		super(ProjectRoleReadView.class);

		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<ProjectRole>() {
					@Override
					public void onEdit(ProjectRole data) {
						EventBus.getInstance().fireEvent(
								new ProjectRoleEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(ProjectRole data) {
						ProjectRoleService projectRoleService = AppContext
								.getSpringBean(ProjectRoleService.class);
						projectRoleService.removeWithSession(data.getId(),
								AppContext.getUsername());
						EventBus.getInstance().fireEvent(
								new ProjectRoleEvent.GotoList(this, null));
					}

					@Override
					public void onClone(ProjectRole data) {
						Role cloneData = (Role) data.copy();
						cloneData.setRolename(null);
						EventBus.getInstance().fireEvent(
								new ProjectRoleEvent.GotoAdd(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new ProjectRoleEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(ProjectRole data) {
						ProjectRoleService projectRoleService = AppContext
								.getSpringBean(ProjectRoleService.class);
						ProjectRoleSearchCriteria criteria = new ProjectRoleSearchCriteria();
						SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = projectRoleService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance()
									.fireEvent(
											new ProjectRoleEvent.GotoRead(this,
													nextId));
						} else {
							view.getWindow()
									.showNotification(
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_GOTO_LAST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(ProjectRole data) {
						ProjectRoleService projectRoleService = AppContext
								.getSpringBean(ProjectRoleService.class);
						ProjectRoleSearchCriteria criteria = new ProjectRoleSearchCriteria();
						SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = projectRoleService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance()
									.fireEvent(
											new ProjectRoleEvent.GotoRead(this,
													nextId));
						} else {
							view.getWindow()
									.showNotification(
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_GOTO_FIRST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectRoleContainer roleContainer = (ProjectRoleContainer) container;

		if (data.getParams() instanceof Integer) {
			ProjectRoleService projectRoleService = AppContext
					.getSpringBean(ProjectRoleService.class);
			SimpleProjectRole role = projectRoleService
					.findById((Integer) data.getParams());
			if (role == null) {
				AppContext
						.getApplication()
						.getMainWindow()
						.showNotification(
								LocalizationHelper
										.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
								LocalizationHelper
										.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
								Window.Notification.TYPE_HUMANIZED_MESSAGE);
				return;
			} else {
				roleContainer.removeAllComponents();
				roleContainer.addComponent(view.getWidget());
				view.previewItem(role);

				ProjectBreadcrumb breadCrumb = ViewManager
						.getView(ProjectBreadcrumb.class);
				breadCrumb.gotoRoleRead(role);
			}
		} else {
			throw new MyCollabException("Do not support screen data: " + data);
		}
	}
}
