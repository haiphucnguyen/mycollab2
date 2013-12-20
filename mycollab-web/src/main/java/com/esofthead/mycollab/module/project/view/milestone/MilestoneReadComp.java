package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.TabsheetLazyLoadComp;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author Mycollab Ltd.
 * @since 3.0
 * 
 */
class MilestoneReadComp extends AbstractMilestonePreviewComp {
	private static final long serialVersionUID = 1L;

	@Override
	protected AdvancedPreviewBeanForm<SimpleMilestone> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleMilestone>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void showHistory() {
				// TODO: show history of milestone
			}
		};
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return new ProjectPreviewFormControlsGenerator<SimpleMilestone>(
				this.previewForm)
				.createButtonControls(ProjectRolePermissionCollections.MILESTONES);
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		final TabsheetLazyLoadComp tabContainer = new TabsheetLazyLoadComp();
		tabContainer.setWidth("100%");

		tabContainer.addTab(this.associateCommentListComp, "Comments",
				MyCollabResource.newResource("icons/16/project/milestone.png"));
		tabContainer.addTab(this.associateTaskGroupListComp, "Related Task",
				MyCollabResource.newResource("icons/16/project/task.png"));
		tabContainer.addTab(this.associateBugListComp, "Related Bugs",
				MyCollabResource.newResource("icons/16/project/bug.png"));

		return tabContainer;
	}

}
