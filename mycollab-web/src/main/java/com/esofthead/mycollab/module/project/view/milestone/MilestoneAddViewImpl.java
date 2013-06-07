/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormContainerHorizontalViewField;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.ProgressIndicator;
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
						(MilestoneAddViewImpl.this.milestone.getId() == null) ? "Create Milestone"
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
					userbox.setRequiredError("Please enter a owner");
					return userbox;
				} else if (propertyId.equals("status")) {
					if (MilestoneAddViewImpl.this.milestone.getStatus() == null) {
						MilestoneAddViewImpl.this.milestone
								.setStatus("In Progress");
					}
					return new ValueComboBox(false, "In Progress", "Future",
							"Closed");
				} else if (propertyId.equals("name")) {
					final TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a Name");
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

					final ProgressIndicator progressTask = new ProgressIndicator(
							new Float((float) (numTasks - numOpenTask)
									/ numTasks));
					progressTask.setPollingInterval(1000000000);
					progressTask.setWidth("120px");
					taskComp.addComponentField(progressTask);
					final Label taskNumber = new Label("(" + numOpenTask + "/"
							+ numTasks + ")");
					taskNumber.setWidth("90px");
					taskComp.addComponentField(taskNumber);
					return taskComp;
				} else if (propertyId.equals("numOpenBugs")) {
					final FormContainerHorizontalViewField bugComp = new FormContainerHorizontalViewField();
					final int numOpenBugs = (MilestoneAddViewImpl.this.milestone instanceof SimpleMilestone) ? ((SimpleMilestone) MilestoneAddViewImpl.this.milestone)
							.getNumOpenBugs() : 0;
					final int numBugs = (MilestoneAddViewImpl.this.milestone instanceof SimpleMilestone) ? ((SimpleMilestone) MilestoneAddViewImpl.this.milestone)
							.getNumBugs() : 0;

					final ProgressIndicator progressBug = new ProgressIndicator(
							new Float((float) (numBugs - numOpenBugs) / numBugs));
					progressBug.setPollingInterval(1000000000);
					progressBug.setWidth("120px");
					bugComp.addComponentField(progressBug);

					final Label bugNumber = new Label("(" + numOpenBugs + "/"
							+ numBugs + ")");
					bugNumber.setWidth("90px");
					bugComp.addComponentField(bugNumber);
					return bugComp;
				}

				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Milestone> getEditFormHandlers() {
		return this.editForm;
	}
}
