package com.esofthead.mycollab.module.crm.view.activity;

import java.util.Collection;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.contact.ContactSelectionField;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class TaskAddViewImpl extends AbstractView implements TaskAddView,
		IFormAddView<Task> {
	private static final long serialVersionUID = 1L;

	private EditForm editForm;

	private Task task;

	public TaskAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(Task item) {
		this.task = item;
		editForm.setItemDataSource(new BeanItem<Task>(task));
	}

	private class EditForm extends AdvancedEditBeanForm<Task> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource,
				Collection<?> propertyIds) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		class FormLayoutFactory extends TaskFormLayoutFactory {

			private Layout createButtonControls() {
				return (new EditFormControlsGenerator<Task>(EditForm.this))
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
				if (propertyId.equals("startdate")) {
					PopupDateField dateField = new PopupDateField();
					dateField.setDateFormat(AppContext.getDateTimeFormat());
					return dateField;
				} else if (propertyId.equals("duedate")) {
					PopupDateField dateField = new PopupDateField();
					dateField.setDateFormat(AppContext.getDateTimeFormat());
					return dateField;
				} else if (propertyId.equals("status")) {
					return new TaskStatusComboBox();
				} else if (propertyId.equals("priority")) {
					return new TaskPriorityComboBox();
				} else if (propertyId.equals("description")) {
					TextArea descArea = new TextArea();
					descArea.setNullRepresentation("");
					return descArea;
				} else if (propertyId.equals("contactid")) {
					ContactSelectionField field = new ContactSelectionField();
					if (task.getContactid() != null) {
						ContactService accountService = AppContext
								.getSpringBean(ContactService.class);
						SimpleContact contact = accountService
								.findContactById(task.getContactid());
						if (contact != null) {
							field.setContact(contact);
						}
					}
					return field;
				} else if (propertyId.equals("subject")) {
					TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Subject must not be null");
					return tf;
				} else if (propertyId.equals("type")) {
					TaskRelatedItemField field = new TaskRelatedItemField();
					return field;
				}
				return null;
			}
		}
	}
}
