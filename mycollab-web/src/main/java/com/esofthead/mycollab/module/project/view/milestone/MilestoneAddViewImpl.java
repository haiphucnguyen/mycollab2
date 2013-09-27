/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormContainerHorizontalViewField;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ProgressBar;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class MilestoneAddViewImpl extends AbstractView implements
		MilestoneAddView {

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;
	private Milestone milestone;

	public MilestoneAddViewImpl() {
		super();
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
		this.setMargin(true);
	}

	@Override
	public void editItem(final Milestone milestone) {
		this.milestone = milestone;
		this.editForm.setItemDataSource(new BeanItem<Milestone>(milestone));
	}

	private class EditForm extends AdvancedEditBeanForm<Milestone> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends MilestoneFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(
						(MilestoneAddViewImpl.this.milestone.getId() == null) ? "Create Phase"
								: MilestoneAddViewImpl.this.milestone.getName());
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<Milestone>(
						EditForm.this)).createButtonControls();
				controlButtons.setSizeUndefined();
				controlPanel.addComponent(controlButtons);
				controlPanel.setWidth("100%");
				controlPanel.setComponentAlignment(controlButtons,
						Alignment.MIDDLE_CENTER);
				return controlPanel;
			}

			@Override
			protected Layout createTopPanel() {
				return this.createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return this.createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("owner")) {
					final ProjectMemberComboBox userbox = new ProjectMemberComboBox();
					userbox.setRequired(true);
					userbox.setRequiredError("Please select an assignee");
					return userbox;
				} else if (propertyId.equals("status")) {
					if (MilestoneAddViewImpl.this.milestone.getStatus() == null) {
						MilestoneAddViewImpl.this.milestone
								.setStatus("In Progress");
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
					final int numOpenTask = (MilestoneAddViewImpl.this.milestone instanceof SimpleMilestone) ? ((SimpleMilestone) MilestoneAddViewImpl.this.milestone)
							.getNumOpenTasks() : 0;
					final int numTasks = (MilestoneAddViewImpl.this.milestone instanceof SimpleMilestone) ? ((SimpleMilestone) MilestoneAddViewImpl.this.milestone)
							.getNumTasks() : 0;

					final ProgressBar progressTask = new ProgressBar(numTasks,
							numOpenTask);
					progressTask.setWidth("100%");
					taskComp.addComponentField(progressTask);
					return taskComp;
				} else if (propertyId.equals("numOpenBugs")) {
					final FormContainerHorizontalViewField bugComp = new FormContainerHorizontalViewField();
					final int numOpenBugs = (MilestoneAddViewImpl.this.milestone instanceof SimpleMilestone) ? ((SimpleMilestone) MilestoneAddViewImpl.this.milestone)
							.getNumOpenBugs() : 0;
					final int numBugs = (MilestoneAddViewImpl.this.milestone instanceof SimpleMilestone) ? ((SimpleMilestone) MilestoneAddViewImpl.this.milestone)
							.getNumBugs() : 0;

					final ProgressBar progressBug = new ProgressBar(numBugs,
							numOpenBugs);
					progressBug.setWidth("100%");
					bugComp.addComponentField(progressBug);
					return bugComp;
				}

				return null;
			}
		}
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

	@Override
	public HasEditFormHandlers<Milestone> getEditFormHandlers() {
		return this.editForm;
	}
}
