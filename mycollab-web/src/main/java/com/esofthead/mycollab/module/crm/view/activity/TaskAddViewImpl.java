package com.esofthead.mycollab.module.crm.view.activity;

import java.util.Collection;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionWindow;
import com.esofthead.mycollab.module.crm.view.contact.ContactSelectionField;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class TaskAddViewImpl extends AbstractView implements TaskAddView {
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

		private class FormLayoutFactory extends TaskFormLayoutFactory {
			private static final long serialVersionUID = 1L;

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
					field.setType(task.getType());
					return field;
				} else if (propertyId.equals("assignuser")) {
					UserComboBox userBox = new UserComboBox();
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

	class TaskRelatedItemField extends CustomField implements FieldSelection {
		private static final long serialVersionUID = 1L;

		private RelatedItemComboBox relatedItemComboBox;

		private TextField itemField;
		private Embedded browseBtn;
		private Embedded clearBtn;

		public TaskRelatedItemField() {

			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);

			relatedItemComboBox = new RelatedItemComboBox();
			layout.addComponent(relatedItemComboBox);

			itemField = new TextField();
			itemField.setEnabled(true);
			layout.addComponent(itemField);
			layout.setComponentAlignment(itemField, Alignment.MIDDLE_LEFT);

			browseBtn = new Embedded(null, new ThemeResource(
					"icons/16/browseItem.png"));
			browseBtn.addListener(new MouseEvents.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
					String type = (String) relatedItemComboBox.getValue();
					if ("Account".equals(type)) {
						AccountSelectionWindow accountWindow = new AccountSelectionWindow(
								TaskRelatedItemField.this);
						getWindow().addWindow(accountWindow);
						accountWindow.show();
					} else if ("Campaign".equals(type)) {

					} else {
						relatedItemComboBox.focus();
					}
				}
			});

			layout.addComponent(browseBtn);
			layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);

			clearBtn = new Embedded(null, new ThemeResource(
					"icons/16/clearItem.png"));
			clearBtn.addListener(new MouseEvents.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void click(ClickEvent event) {
					task.setType("");
					task.setTypeid(null);
				}
			});

			layout.addComponent(clearBtn);
			layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

			this.setCompositionRoot(layout);
		}

		@Override
		public Class<?> getType() {
			return (new String[2]).getClass();
		}

		private class RelatedItemComboBox extends ValueComboBox {
			private static final long serialVersionUID = 1L;

			public RelatedItemComboBox() {
				super();
				setCaption(null);
				this.setWidth("100px");
				this.loadData(new String[] { "Account", "Campaign", "Contact",
						"Lead", "Opportunity", "Case" });
			}
		}

		public void setType(String type) {
			relatedItemComboBox.select(type);
		}

		@Override
		public void fireValueChange(Object data) {
			if (data instanceof SimpleAccount) {
				task.setType("Account");
				task.setTypeid(((SimpleAccount) data).getId());
				itemField.setValue(((SimpleAccount) data).getAccountname());
			}
		}

	}
}
