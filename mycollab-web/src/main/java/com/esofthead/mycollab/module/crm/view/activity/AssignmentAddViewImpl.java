package com.esofthead.mycollab.module.crm.view.activity;

import java.util.Collection;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedEditItemField;
import com.esofthead.mycollab.module.crm.view.CrmDataTypeFactory;
import com.esofthead.mycollab.module.crm.view.contact.ContactSelectionField;
import com.esofthead.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

@ViewComponent
public class AssignmentAddViewImpl extends AbstractView implements
		AssignmentAddView {

	private static final long serialVersionUID = 1L;
	private EditForm editForm;
	private Task task;

	public AssignmentAddViewImpl() {
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

		private class FormLayoutFactory extends AssignmentFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super((task.getId() == null) ? "Create Task" : task
						.getSubject());
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<Task>(
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
				return createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return null;
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
						SimpleContact contact = accountService.findById(
								task.getContactid(), AppContext.getAccountId());
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
					RelatedEditItemField field = new RelatedEditItemField(
							new String[] { CrmTypeConstants.ACCOUNT,
									CrmTypeConstants.CAMPAIGN,
									CrmTypeConstants.CONTACT,
									CrmTypeConstants.LEAD,
									CrmTypeConstants.OPPORTUNITY,
									CrmTypeConstants.CASE }, task);
					field.setType(task.getType());

					return field;
				} else if (propertyId.equals("assignuser")) {
					ActiveUserComboBox userBox = new ActiveUserComboBox();
					userBox.select(task.getAssignuser());
					return userBox;
				}
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Task> getEditFormHandlers() {
		return editForm;
	}

	class TaskPriorityComboBox extends ValueComboBox {

		private static final long serialVersionUID = 1L;

		public TaskPriorityComboBox() {
			super();
			setCaption(null);
			this.loadData(CrmDataTypeFactory.getTaskPriorities());
		}
	}

	class TaskStatusComboBox extends ValueComboBox {

		private static final long serialVersionUID = 1L;

		public TaskStatusComboBox() {
			super();
			setCaption(null);
			this.loadData(CrmDataTypeFactory.getTaskStatuses());
		}
	}
}
