package com.esofthead.mycollab.mobile.module.project.view.settings;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.mobile.ui.AbstractMobilePresenter;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.i18n.ProjectMemberI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.PageView.ViewEvent;
import com.esofthead.mycollab.vaadin.mvp.PageView.ViewListener;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public class ProjectMemberInvitePresenter extends
		AbstractMobilePresenter<ProjectMemberInviteView> {
	private static final long serialVersionUID = 1L;

	public ProjectMemberInvitePresenter() {
		super(ProjectMemberInviteView.class);
	}

	@Override
	protected void postInitView() {
		view.addViewListener(new ViewListener<ProjectMemberEvent.InviteProjectMembers>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void receiveEvent(
					ViewEvent<ProjectMemberEvent.InviteProjectMembers> event) {
				ProjectMemberEvent.InviteProjectMembers inviteMembers = (ProjectMemberEvent.InviteProjectMembers) event
						.getData();
				ProjectMemberService projectMemberService = ApplicationContextUtil
						.getSpringBean(ProjectMemberService.class);
				List<String> inviteEmails = inviteMembers.getInviteEmails();
				if (CollectionUtils.isNotEmpty(inviteEmails)) {
					projectMemberService.inviteProjectMembers(
							inviteEmails.toArray(new String[0]),
							CurrentProjectVariables.getProjectId(),
							inviteMembers.getRoleId(),
							AppContext.getUsername(),
							inviteMembers.getInviteMessage(),
							AppContext.getAccountId());

					EventBusFactory.getInstance().post(
							new ShellEvent.NavigateBack(this, null));
				}

			}
		});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.USERS)) {
			super.onGo(container, data);
			view.display();
			AppContext
					.addFragment(
							"project/user/invite/"
									+ GenericLinkUtils
											.encodeParam(new Object[] { CurrentProjectVariables
													.getProjectId() }),
							AppContext
									.getMessage(ProjectMemberI18nEnum.FORM_NEW_TITLE));

		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}

}
