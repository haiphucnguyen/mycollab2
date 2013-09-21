/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectMemberAddPresenter extends
		AbstractPresenter<ProjectMemberAddView> {
	private static final long serialVersionUID = 1L;

	public ProjectMemberAddPresenter() {
		super(ProjectMemberAddView.class);
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<ProjectMember>() {
					@Override
					public void onSave(final ProjectMember projectMember) {
						saveProjectMember(projectMember);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance()
									.fireEvent(
											new ProjectMemberEvent.GotoList(
													this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance()
									.fireEvent(
											new ProjectMemberEvent.GotoList(
													this, null));
						}
					}

					@Override
					public void onSaveAndNew(final ProjectMember projectMember) {
						saveProjectMember(projectMember);
						EventBus.getInstance().fireEvent(
								new ProjectMemberEvent.GotoAdd(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.USERS)) {
			ProjectMemberContainer userGroupContainer = (ProjectMemberContainer) container;
			userGroupContainer.removeAllComponents();
			userGroupContainer.addComponent(view.getWidget());

			ProjectMember member = (ProjectMember) data.getParams();
			view.editItem(member);

			ProjectBreadcrumb breadcrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			if (member.getId() == null) {
				breadcrumb.gotoUserAdd();
			} else {
				breadcrumb.gotoUserEdit((SimpleProjectMember) member);
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	public void saveProjectMember(ProjectMember projectMember) {
		ProjectMemberService projectMemberService = ApplicationContextUtil
				.getSpringBean(ProjectMemberService.class);

		if (projectMember.getId() == null) {
			UserService userService = ApplicationContextUtil
					.getSpringBean(UserService.class);
			User user = userService.findUserByUserName(projectMember
					.getUsername());
			if (user != null) {
				projectMemberService.inviteProjectMember(
						new String[] { user.getEmail() },
						CurrentProjectVariables.getProjectId(),
						projectMember.getProjectroleid(),
						AppContext.getUsername(), AppContext.getAccountId());
			} else {
				throw new MyCollabException(
						"User not exist in projectMember table, something goes wrong in DB");
			}

		} else {
			projectMemberService.updateWithSession(projectMember,
					AppContext.getUsername());
		}

	}

}
