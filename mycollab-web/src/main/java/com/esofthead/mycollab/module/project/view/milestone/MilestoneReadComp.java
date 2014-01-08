package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.project.ui.components.CommentDisplay;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.schedule.email.project.ProjectMilestoneRelayEmailNotificationAction;
import com.esofthead.mycollab.vaadin.resource.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.resource.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.resource.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.resource.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.resource.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.resource.ui.ProgressBarIndicator;
import com.esofthead.mycollab.vaadin.resource.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.resource.ui.TabsheetLazyLoadComp;
import com.esofthead.mycollab.vaadin.resource.ui.DefaultFormViewFieldFactory.FormContainerHorizontalViewField;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;

/**
 * 
 * @author Mycollab Ltd.
 * @since 3.0
 * 
 */
class MilestoneReadComp extends AbstractPreviewItemComp<SimpleMilestone> {
	private static final long serialVersionUID = 1L;

	protected CommentDisplay associateCommentListComp;

	protected MilestoneHistoryLogList historyList;

	protected MilestoneBugListComp associateBugListComp;

	protected MilestoneTaskGroupListComp associateTaskGroupListComp;

	MilestoneReadComp() {
		super(MyCollabResource
				.newResource("icons/22/project/menu_milestone.png"));
	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleMilestone> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleMilestone>();
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
				MyCollabResource
						.newResource("icons/16/project/gray/comment.png"));
		tabContainer.addTab(historyList, "History", MyCollabResource
				.newResource("icons/16/project/gray/history.png"));
		tabContainer.addTab(this.associateTaskGroupListComp, "Related Task",
				MyCollabResource.newResource("icons/16/project/gray/task.png"));
		tabContainer.addTab(this.associateBugListComp, "Related Bugs",
				MyCollabResource.newResource("icons/16/project/gray/bug.png"));

		return tabContainer;
	}

	@Override
	protected void initRelatedComponents() {
		this.historyList = new MilestoneHistoryLogList(ModuleNameConstants.PRJ,
				ProjectContants.MILESTONE);
		this.associateBugListComp = new MilestoneBugListComp();
		this.associateTaskGroupListComp = new MilestoneTaskGroupListComp();
		this.associateCommentListComp = new CommentDisplay(
				CommentType.PRJ_MILESTONE,
				CurrentProjectVariables.getProjectId(), true, true,
				ProjectMilestoneRelayEmailNotificationAction.class);
	}

	@Override
	protected void onPreviewItem() {
		displayTaskGroups();
		displayBugs();
		displayComments();

		historyList.loadHistory(beanItem.getId());
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getName();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new MilestoneFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleMilestone> initBeanFormFieldFactory() {
		return new MilestoneFormFieldFactory(previewForm);
	}

	protected void displayBugs() {
		this.associateBugListComp.displayBugs(this.beanItem);
	}

	protected void displayComments() {
		this.associateCommentListComp.loadComments(this.beanItem.getId());
	}

	protected void displayTaskGroups() {
		this.associateTaskGroupListComp.displayTakLists(this.beanItem);
	}

	private static class MilestoneFormFieldFactory extends
			AbstractBeanFieldGroupViewFieldFactory<SimpleMilestone> {

		private static final long serialVersionUID = 1L;

		public MilestoneFormFieldFactory(GenericBeanForm<SimpleMilestone> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(final Object propertyId) {
			SimpleMilestone milestone = attachForm.getBean();
			if (propertyId.equals("startdate")) {
				return new DefaultFormViewFieldFactory.FormDateViewField(
						milestone.getStartdate());
			} else if (propertyId.equals("enddate")) {
				return new DefaultFormViewFieldFactory.FormDateViewField(
						milestone.getEnddate());
			} else if (propertyId.equals("owner")) {
				return new ProjectUserFormLinkField(milestone.getOwner(),
						milestone.getOwnerAvatarId(),
						milestone.getOwnerFullName());
			} else if (propertyId.equals("description")) {
				return new DefaultFormViewFieldFactory.FormDetectAndDisplayUrlViewField(
						milestone.getDescription());
			} else if (propertyId.equals("numOpenTasks")) {
				final FormContainerHorizontalViewField taskComp = new FormContainerHorizontalViewField();

				final ProgressBarIndicator progressTask = new ProgressBarIndicator(
						milestone.getNumTasks(), milestone.getNumOpenTasks());
				progressTask.setWidth("100%");
				taskComp.addComponentField(progressTask);
				return taskComp;
			} else if (propertyId.equals("numOpenBugs")) {
				final FormContainerHorizontalViewField bugComp = new FormContainerHorizontalViewField();

				final ProgressBarIndicator progressBug = new ProgressBarIndicator(
						milestone.getNumBugs(), milestone.getNumOpenBugs());
				progressBug.setWidth("100%");
				bugComp.addComponentField(progressBug);
				return bugComp;
			}
			return null;
		}
	}

}
