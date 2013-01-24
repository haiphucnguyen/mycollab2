/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import java.util.Collection;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class VersionAddViewImpl extends AbstractView implements VersionAddView {
	private static final long serialVersionUID = 1L;
	private EditForm editForm;
	private Version version;

	public VersionAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(Version item) {
		this.version = item;
		editForm.setItemDataSource(new BeanItem<Version>(version));
	}

	private class EditForm extends AdvancedEditBeanForm<Version> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource,
				Collection<?> propertyIds) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		private class FormLayoutFactory extends VersionFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super("Create Version");
			}

			private Layout createButtonControls() {
				return (new EditFormControlsGenerator<Version>(EditForm.this))
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

				if (propertyId.equals("versionname")) {
					TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a Version Name");
					return tf;
				} else if (propertyId.equals("description")) {
					TextArea field = new TextArea("", "");
					field.setNullRepresentation("");
					return field;
				} else if (propertyId.equals("duedate")) {
					DateField dateField = new DateField();
					dateField.setResolution(PopupDateField.RESOLUTION_DAY);
					return dateField;
				}

				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Version> getEditFormHandlers() {
		return editForm;
	}
}
