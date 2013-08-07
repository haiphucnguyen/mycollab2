/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectMemberStatusContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.schedule.email.project.MessageRelayEmailNotificationAction;
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
		ProjectMemberService projectMemberService = AppContext
				.getSpringBean(ProjectMemberService.class);
		projectMember.setProjectid(CurrentProjectVariables.getProjectId());
		projectMember.setJoindate(new GregorianCalendar().getTime());
		projectMember.setStatus(ProjectMemberStatusContants.VERIFICATING);
		if (projectMember.getId() == null) {
			int saveId = projectMemberService.saveWithSession(projectMember,
					AppContext.getUsername());
			if (saveId > 0) {
				RelayEmailNotification relayNotification = new RelayEmailNotification();
				relayNotification.setChangeby(AppContext.getUsername());
				relayNotification.setChangecomment("");
				int sAccountId = AppContext.getAccountId();
				relayNotification.setSaccountid(sAccountId);
				relayNotification.setType("invitationMember");
				relayNotification.setAction(MonitorTypeConstants.CREATE_ACTION);
				relayNotification.setTypeid(saveId);
				relayNotification
						.setEmailhandlerbean(MessageRelayEmailNotificationAction.class
								.getName());
				RelayEmailNotificationService relayEmailNotificationService = AppContext
						.getSpringBean(RelayEmailNotificationService.class);
				relayEmailNotificationService.saveWithSession(
						relayNotification, AppContext.getUsername());
			}

		} else {
			projectMemberService.updateWithSession(projectMember,
					AppContext.getUsername());
		}

	}

}
