package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.TabsheetLazyLoadComp;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class ProjectMemberReadComp extends AbstractMemberPreviewComp {
	private static final long serialVersionUID = 1L;

	@Override
	protected AdvancedPreviewBeanForm<SimpleProjectMember> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleProjectMember>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void showHistory() {
				final ProjectMemberHistoryLogWindow historyLog = new ProjectMemberHistoryLogWindow(
						ModuleNameConstants.PRJ,
						ProjectContants.PROJECT_MEMBER, previewForm.getBean()
								.getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return new ProjectPreviewFormControlsGenerator<SimpleProjectMember>(
				previewForm)
				.createButtonControls(ProjectRolePermissionCollections.USERS);
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		final TabsheetLazyLoadComp tabContainer = new TabsheetLazyLoadComp();
		tabContainer.setWidth("100%");
		tabContainer.addTab(this.userActivityComp, "Activities");
		tabContainer.addTab(this.standupComp, "Stand Ups");
		tabContainer.addTab(this.userTaskComp, "Task Assignments");
		tabContainer.addTab(this.userBugComp, "Bug Assignments");
		return tabContainer;
	}
}
