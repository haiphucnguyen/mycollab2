package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberComboBox;
import com.esofthead.mycollab.vaadin.resource.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.resource.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.resource.ui.ProgressBarIndicator;
import com.esofthead.mycollab.vaadin.resource.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.resource.ui.DefaultFormViewFieldFactory.FormContainerHorizontalViewField;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 * @param <B>
 */
public class MilestoneEditFormFieldFactory<B extends Milestone> extends
		AbstractBeanFieldGroupEditFieldFactory<B> {

	private static final long serialVersionUID = 1L;

	MilestoneEditFormFieldFactory(GenericBeanForm<B> form) {
		super(form);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		if (propertyId.equals("owner")) {
			final ProjectMemberComboBox userbox = new ProjectMemberComboBox();
			userbox.setRequired(true);
			userbox.setRequiredError("Please select an assignee");
			return userbox;
		} else if (propertyId.equals("status")) {
			if (attachForm.getBean().getStatus() == null) {
				attachForm.getBean().setStatus("In Progress");
			}
			return new ProgressStatusComboBox();
		} else if (propertyId.equals("name")) {
			final TextField tf = new TextField();
			tf.setNullRepresentation("");
			tf.setRequired(true);
			tf.setRequiredError("Please enter name");
			return tf;
		} else if (propertyId.equals("description")) {
			final RichTextArea descArea = new RichTextArea();
			descArea.setNullRepresentation("");
			return descArea;
		} else if (propertyId.equals("numOpenTasks")) {
			final FormContainerHorizontalViewField taskComp = new FormContainerHorizontalViewField();
			final int numOpenTask = (attachForm.getBean() instanceof SimpleMilestone) ? ((SimpleMilestone) attachForm
					.getBean()).getNumOpenTasks() : 0;
			final int numTasks = (attachForm.getBean() instanceof SimpleMilestone) ? ((SimpleMilestone) attachForm
					.getBean()).getNumTasks() : 0;

			final ProgressBarIndicator progressTask = new ProgressBarIndicator(
					numTasks, numOpenTask);
			progressTask.setWidth("100%");
			taskComp.addComponentField(progressTask);
			return taskComp;
		} else if (propertyId.equals("numOpenBugs")) {
			final FormContainerHorizontalViewField bugComp = new FormContainerHorizontalViewField();
			final int numOpenBugs = (attachForm.getBean() instanceof SimpleMilestone) ? ((SimpleMilestone) attachForm
					.getBean()).getNumOpenBugs() : 0;
			final int numBugs = (attachForm.getBean() instanceof SimpleMilestone) ? ((SimpleMilestone) attachForm
					.getBean()).getNumBugs() : 0;

			final ProgressBarIndicator progressBug = new ProgressBarIndicator(
					numBugs, numOpenBugs);
			progressBug.setWidth("100%");
			bugComp.addComponentField(progressBug);
			return bugComp;
		}

		return null;
	}

	private static class ProgressStatusComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;

		public ProgressStatusComboBox() {
			super(false, MilestoneStatusConstant.IN_PROGRESS,
					MilestoneStatusConstant.FUTURE,
					MilestoneStatusConstant.CLOSED);
			this.setItemIcon(MilestoneStatusConstant.IN_PROGRESS,
					MyCollabResource
							.newResource("icons/16/project/phase_progress.png"));
			this.setItemIcon(MilestoneStatusConstant.FUTURE, MyCollabResource
					.newResource("icons/16/project/phase_future.png"));
			this.setItemIcon(MilestoneStatusConstant.CLOSED, MyCollabResource
					.newResource("icons/16/project/phase_closed.png"));
		}
	}

}
