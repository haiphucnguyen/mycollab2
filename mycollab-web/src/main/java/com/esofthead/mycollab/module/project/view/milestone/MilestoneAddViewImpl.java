/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
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
	private EditForm editForm;
	private Milestone milestone;

	public MilestoneAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(Milestone milestone) {
		this.milestone = milestone;
		editForm.setItemDataSource(new BeanItem<Milestone>(milestone));
	}

	private class EditForm extends AdvancedEditBeanForm<Milestone> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends MilestoneFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super("Create Milestone");
			}

			private Layout createButtonControls() {
				return (new EditFormControlsGenerator<Milestone>(EditForm.this))
						.createButtonControls();
			}

			@Override
			protected Layout createTopPanel() {
				return createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("owner")) {
					ProjectMemberComboBox userbox = new ProjectMemberComboBox();
					userbox.setRequired(true);
					userbox.setRequiredError("Please enter a owner");
					return userbox;
				} else if (propertyId.equals("status")) {
					if (milestone.getStatus() == null) {
						milestone.setStatus("In Progress");
					}
					return new ValueComboBox(false, "In Progress", "Future",
							"Closed");
				} else if (propertyId.equals("name")) {
					TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a Name");
					return tf;
				} else if (propertyId.equals("description")) {
					RichTextArea descArea = new RichTextArea();
					descArea.setNullRepresentation("");
					return descArea;
				}

				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Milestone> getEditFormHandlers() {
		return editForm;
	}
}
