/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.CurrencyComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
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
@SuppressWarnings("serial")
@ViewComponent
public class ProjectAddViewImpl extends AbstractView implements ProjectAddView {

	private Project project;
	private final EditForm editForm;

	public ProjectAddViewImpl() {
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
		this.setMargin(true);
	}

	@Override
	public HasEditFormHandlers<Project> getEditFormHandlers() {
		return this.editForm;
	}

	@Override
	public void editItem(final Project item) {
		this.project = item;
		this.editForm.setItemDataSource(new BeanItem<Project>(this.project));
	}

	private class EditForm extends AdvancedEditBeanForm<Project> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends ProjectFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(
						(ProjectAddViewImpl.this.project.getId() == null) ? "Create Project"
								: ProjectAddViewImpl.this.project.getName());
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons;

				if (ProjectAddViewImpl.this.project.getId() == null) {
					controlButtons = (new EditFormControlsGenerator<Project>(
							ProjectAddViewImpl.EditForm.this))
							.createButtonControls();
				} else {
					controlButtons = (new EditFormControlsGenerator<Project>(
							ProjectAddViewImpl.EditForm.this))
							.createButtonControls(true, false, true);
				}
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
				if (propertyId.equals("description")) {
					final RichTextArea field = new RichTextArea();
					field.setHeight("350px");
					return field;
				} else if (propertyId.equals("projectstatus")) {
					final ProjectStatusComboBox projectCombo = new ProjectStatusComboBox();
					projectCombo.setRequired(true);
					projectCombo
							.setRequiredError("Please enter a project status");
					if (ProjectAddViewImpl.this.project.getProjectstatus() == null) {
						ProjectAddViewImpl.this.project
								.setProjectstatus("Open");
					}
					return projectCombo;
				} else if (propertyId.equals("shortname")) {
					final TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a project short name");
					return tf;
				} else if (propertyId.equals("currencyid")) {
					return new CurrencyComboBox();
				} else if (propertyId.equals("name")) {
					final TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a Name");
					return tf;
				}

				return null;
			}
		}
	}

	private static class ProjectStatusComboBox extends ValueComboBox {

		public ProjectStatusComboBox() {
			super(false, ProjectDataTypeFactory.getProjectStatusList());
		}
	}

	@Override
	public Project getItem() {
		return this.project;
	}
}
