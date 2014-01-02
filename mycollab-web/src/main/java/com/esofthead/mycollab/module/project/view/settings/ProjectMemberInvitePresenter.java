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

import java.util.List;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent.InviteProjectMembers;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectMemberInvitePresenter extends
		AbstractPresenter<ProjectMemberInviteView> {
	private static final long serialVersionUID = 1L;

	public ProjectMemberInvitePresenter() {
		super(ProjectMemberInviteView.class);
	}

	@Override
	protected void postInitView() {
		view.addViewListener(new ApplicationEventListener<ProjectMemberEvent.InviteProjectMembers>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return ProjectMemberEvent.InviteProjectMembers.class;
			}

			@Override
			public void handle(InviteProjectMembers event) {
				ProjectMemberService projectMemberService = ApplicationContextUtil
						.getSpringBean(ProjectMemberService.class);
				List<String> inviteEmails = event.getInviteEmails();
				if (inviteEmails != null && inviteEmails.size() > 0) {
					projectMemberService.inviteProjectMember(
							inviteEmails.toArray(new String[0]),
							CurrentProjectVariables.getProjectId(),
							event.getRoleId(), AppContext.getUsername(),
							AppContext.getAccountId());

					EventBus.getInstance().fireEvent(
							new ProjectMemberEvent.GotoList(this, null));
				}

			}
		});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.USERS)) {
			ProjectUserContainer userGroupContainer = (ProjectUserContainer) container;
			userGroupContainer.removeAllComponents();
			userGroupContainer.addComponent(view.getWidget());

			view.display();

			ProjectBreadcrumb breadcrumb = ViewManager
					.getView(ProjectBreadcrumb.class);

			breadcrumb.gotoUserAdd();
		} else {
			NotificationUtil.showMessagePermissionAlert();
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
