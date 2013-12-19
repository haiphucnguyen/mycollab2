package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class ProjectRoleReadComp extends AbstractProjectRolePreviewComp {
	private static final long serialVersionUID = 1L;

	@Override
	protected AdvancedPreviewBeanForm<SimpleProjectRole> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleProjectRole>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void showHistory() {
				final ProjectRoleHistoryLogWindow historyLog = new ProjectRoleHistoryLogWindow(
						ModuleNameConstants.PRJ, ProjectContants.PROJECT_ROLE,
						previewForm.getBean().getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return (new ProjectPreviewFormControlsGenerator<SimpleProjectRole>(
				previewForm))
				.createButtonControls(ProjectRolePermissionCollections.ROLES);
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		final VerticalLayout permissionsPanel = new VerticalLayout();
		final Label organizationHeader = new Label("Permissions");
		organizationHeader.setStyleName("h2");
		permissionsPanel.addComponent(organizationHeader);

		projectFormHelper = new GridFormLayoutHelper(2,
				ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length,
				"100%", "167px", Alignment.MIDDLE_LEFT);

		permissionsPanel.addComponent(projectFormHelper.getLayout());

		return permissionsPanel;
	}
}
