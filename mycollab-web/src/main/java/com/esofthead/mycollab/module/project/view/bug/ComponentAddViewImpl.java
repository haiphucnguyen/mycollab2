/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.Collection;

import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberComboBox;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ComponentAddViewImpl extends AbstractView implements
		ComponentAddView {

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;
	private Component component;

	public ComponentAddViewImpl() {
		super();
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
		this.setMargin(true);
	}

	@Override
	public void editItem(final Component item) {
		this.component = item;
		this.editForm
				.setItemDataSource(new BeanItem<Component>(this.component));
	}

	private class EditForm extends AdvancedEditBeanForm<Component> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource,
				final Collection<?> propertyIds) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		private class FormLayoutFactory extends ComponentFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super("Create Component");
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<Component>(
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

				if (propertyId.equals("componentname")) {
					final TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a Component Name");
					return tf;
				} else if (propertyId.equals("description")) {
					final TextArea field = new TextArea("", "");
					field.setNullRepresentation("");
					return field;
				} else if (propertyId.equals("userlead")) {
					final ProjectMemberComboBox userBox = new ProjectMemberComboBox();
					return userBox;
				}

				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Component> getEditFormHandlers() {
		return this.editForm;
	}

}
