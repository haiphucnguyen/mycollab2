package com.esofthead.mycollab.mobile.module.project.view.settings;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.mobile.ui.AbstractMobilePresenter;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
public class ProjectMemberEditPresenter extends
		AbstractMobilePresenter<ProjectMemberEditView> {

	private static final long serialVersionUID = -209370866970403913L;

	public ProjectMemberEditPresenter() {
		super(ProjectMemberEditView.class);
	}

	@Override
	protected void postInitView() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<SimpleProjectMember>() {
					private static final long serialVersionUID = 1L;

					@Override
					public void onSave(final SimpleProjectMember projectMember) {
						saveProjectMember(projectMember);
						EventBusFactory.getInstance().post(
								new ShellEvent.NavigateBack(this, null));
					}

					@Override
					public void onCancel() {
					}

					@Override
					public void onSaveAndNew(
							final SimpleProjectMember projectMember) {

					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.USERS)) {

			SimpleProjectMember member = (SimpleProjectMember) data.getParams();
			super.onGo(container, data);
			view.editItem(member);

			AppContext.addFragment(
					"project/user/edit/"
							+ GenericLinkUtils.encodeParam(new Object[] {
									CurrentProjectVariables.getProjectId(),
									member.getId() }),
					AppContext.getMessage(MilestoneI18nEnum.FORM_NEW_TITLE));
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}

	}

	public void saveProjectMember(SimpleProjectMember projectMember) {
		ProjectMemberService projectMemberService = ApplicationContextUtil
				.getSpringBean(ProjectMemberService.class);
		projectMemberService.updateWithSession(projectMember,
				AppContext.getUsername());

	}

}
