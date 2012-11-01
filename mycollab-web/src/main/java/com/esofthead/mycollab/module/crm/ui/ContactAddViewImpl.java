package com.esofthead.mycollab.module.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.ui.components.AccountSelectionField;
import com.esofthead.mycollab.module.crm.ui.components.LeadSourceComboBox;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ui.Params;
import com.esofthead.mycollab.vaadin.ui.AdvancedBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@Component
public class ContactAddViewImpl extends AbstractView implements ContactAddView {
	private static final long serialVersionUID = 1L;

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);

		return layout;
	}

	@Override
	public void handleRequest(Params params) {
		super.handleRequest(params);

		compContainer.removeAllComponents();
		Form formItem = null;

		if (Params.EDIT.equals(params.getAction())) {
			formItem = AppContext.getSpringBean(EditForm.class);
			formItem.setItemDataSource(new BeanItem<Contact>((Contact) params
					.getParameters()[0]));
		} else if (Params.ADD.equals(params.getAction())) {
			formItem = AppContext.getSpringBean(EditForm.class);
			formItem.setItemDataSource(new BeanItem<Contact>(new Contact()));
		} else if (Params.VIEW.equals(params.getAction())) {
			formItem = AppContext.getSpringBean(ViewForm.class);
			formItem.setItemDataSource(new BeanItem<Contact>((Contact) params
					.getParameters()[0]));
		}

		compContainer.addComponent(formItem);
	}

	@Scope("prototype")
	@Component
	public static class EditForm extends GenericForm {
		private static final long serialVersionUID = 1L;

		@Override
		protected HorizontalLayout createButtonControls() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			FormActionListener formActionListener = new FormActionListener();
			Button saveBtn = new Button(SAVE_ACTION, formActionListener);
			Button cancelBtn = new Button(CANCEL_ACTION, formActionListener);

			layout.addComponent(saveBtn);
			layout.addComponent(cancelBtn);
			return layout;
		}

		@PostConstruct
		private void initFieldFactory() {
			this.setFormFieldFactory(new EditFormFieldFactory());
		}

		private class EditFormFieldFactory extends DefaultFieldFactory {
			private static final long serialVersionUID = 1L;

			@Override
			public Field createField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {

				Field field = super.createField(item, propertyId, uiContext);

				if (propertyId.equals("leadsource")) {
					LeadSourceComboBox leadSource = AppContext
							.getSpringBean(LeadSourceComboBox.class);
					return leadSource;
				} else if (propertyId.equals("accountid")) {
					AccountSelectionField accountField = AppContext
							.getSpringBean(AccountSelectionField.class);
					return accountField;
				}

				if (field instanceof TextField) {
					((TextField) field).setNullRepresentation("");
					((TextField) field)
							.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
					((TextField) field).setCaption(null);
				}

				return field;
			}
		}

		private class FormActionListener implements Button.ClickListener {
			private static final long serialVersionUID = 1L;

			// ==================================================
			// Actions
			// ==================================================
			@Override
			public void buttonClick(ClickEvent event) {
				String caption = event.getButton().getCaption();
				@SuppressWarnings("unchecked")
				Contact account = ((BeanItem<Contact>) EditForm.this
						.getItemDataSource()).getBean();
				if (caption.equals(SAVE_ACTION)) {
					if (validateForm(account)) {
						eventBus.fireEvent(new ContactEvent(this,
								ContactEvent.SAVE, account));
					}
				} else if (caption.equals(CANCEL_ACTION)) {
					eventBus.fireEvent(new ContactEvent(this,
							ContactEvent.GOTO_LIST_VIEW, account));
				}
			}
		}
	}

	@Scope("prototype")
	@Component
	public static class ViewForm extends GenericForm {
		private static final long serialVersionUID = 1L;

		@Override
		protected HorizontalLayout createButtonControls() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			FormActionListener formActionListener = new FormActionListener();
			Button saveBtn = new Button(EDIT_ACTION, formActionListener);
			Button cancelBtn = new Button(CANCEL_ACTION, formActionListener);

			layout.addComponent(saveBtn);
			layout.addComponent(cancelBtn);
			return layout;
		}

		@PostConstruct
		private void initFieldFactory() {
			this.setFormFieldFactory(new DefaultFormViewFieldFactory());
		}

		private class FormActionListener implements Button.ClickListener {
			private static final long serialVersionUID = 1L;

			// ==================================================
			// Actions
			// ==================================================
			@Override
			public void buttonClick(ClickEvent event) {
				String caption = event.getButton().getCaption();
				@SuppressWarnings("unchecked")
				Contact contact = ((BeanItem<Contact>) ViewForm.this
						.getItemDataSource()).getBean();
				if (caption.equals(EDIT_ACTION)) {
					eventBus.fireEvent(new ContactEvent(this,
							ContactEvent.GOTO_EDIT_VIEW, contact));
				} else if (caption.equals(CANCEL_ACTION)) {
					eventBus.fireEvent(new ContactEvent(this,
							ContactEvent.GOTO_LIST_VIEW, contact));
				}
			}
		}

	}

	public static abstract class GenericForm extends AdvancedBeanForm<Contact> {
		private static final long serialVersionUID = 1L;

		protected GridFormLayoutHelper informationLayout;

		protected GridFormLayoutHelper addressLayout;

		public GenericForm() {
			super();
			this.setCaption("Create Contact");

			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);

			layout.addComponent(createButtonControls());

			Label organizationHeader = new Label("Contact Information");
			organizationHeader.setStyleName(Reindeer.LABEL_H2);
			layout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 9);
			layout.addComponent(informationLayout.getLayout());

			Label addressHeader = new Label("Address Information");
			addressHeader.setStyleName(Reindeer.LABEL_H2);
			layout.addComponent(addressHeader);
			addressLayout = new GridFormLayoutHelper(2, 5);
			layout.addComponent(addressLayout.getLayout());

			this.setWriteThrough(true);
			this.setInvalidCommitted(false);

			layout.addComponent(createButtonControls());
			setLayout(layout);
		}

		abstract protected HorizontalLayout createButtonControls();

		/*
		 * Override to get control over where fields are placed.
		 */
		@Override
		protected void attachField(Object propertyId, Field field) {
			informationLayout.addComponent(propertyId.equals("firstname"),
					field, "First Name", 0, 0);

			informationLayout.addComponent(propertyId.equals("lastname"),
					field, "Last Name", 0, 1);

			informationLayout.addComponent(propertyId.equals("accountid"),
					field, "Account", 0, 2);

			informationLayout.addComponent(propertyId.equals("title"), field,
					"Title", 0, 3);

			informationLayout.addComponent(propertyId.equals("department"),
					field, "Department", 0, 4);

			informationLayout.addComponent(propertyId.equals("email"), field,
					"Email", 0, 5);

			informationLayout.addComponent(propertyId.equals("assistant"),
					field, "Assistant", 0, 6);

			informationLayout.addComponent(propertyId.equals("assistantphone"),
					field, "Assistant Phone", 0, 7);
			informationLayout.addComponent(propertyId.equals("leadsource"),
					field, "Leade Source", 0, 8);

			informationLayout.addComponent(propertyId.equals("officephone"),
					field, "Phone Office", 1, 0);

			informationLayout.addComponent(propertyId.equals("mobile"), field,
					"Mobile", 1, 1);

			informationLayout.addComponent(propertyId.equals("homephone"),
					field, "Home Phone", 1, 2);

			informationLayout.addComponent(propertyId.equals("otherphone"),
					field, "Other Phone", 1, 3);

			informationLayout.addComponent(propertyId.equals("fax"), field,
					"Fax", 1, 4);

			informationLayout.addComponent(propertyId.equals("birthday"),
					field, "Birthday", 1, 5);

			informationLayout.addComponent(propertyId.equals("iscallable"),
					field, "Callable", 1, 6);

			informationLayout.addComponent(propertyId.equals("assignuser"),
					field, "Assign User", 1, 7);

			informationLayout.addComponent(propertyId.equals("campaignid"),
					field, "Campaign", 1, 8);

			addressLayout.addComponent(propertyId.equals("primaddress"), field,
					"Address", 0, 0);
			addressLayout.addComponent(propertyId.equals("primcity"), field,
					"City", 0, 1);
			addressLayout.addComponent(propertyId.equals("primstate"), field,
					"State", 0, 2);
			addressLayout.addComponent(propertyId.equals("primpostalcode"),
					field, "Postal Code", 0, 3);
			addressLayout.addComponent(propertyId.equals("primcountry"), field,
					"Country", 0, 4);

			addressLayout.addComponent(propertyId.equals("otheraddress"),
					field, "Other Address", 1, 0);
			addressLayout.addComponent(propertyId.equals("othercity"), field,
					"Other City", 1, 1);
			addressLayout.addComponent(propertyId.equals("otherstate"), field,
					"Other State", 1, 2);
			addressLayout.addComponent(propertyId.equals("otherpostalcode"),
					field, "Other Postal Code", 1, 3);
			addressLayout.addComponent(propertyId.equals("othercountry"),
					field, "Other Country", 1, 4);

		}
	}

}
