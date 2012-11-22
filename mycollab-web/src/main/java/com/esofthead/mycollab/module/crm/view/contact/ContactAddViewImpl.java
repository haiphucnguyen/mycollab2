package com.esofthead.mycollab.module.crm.view.contact;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.ui.components.AccountSelectionField;
import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.module.crm.ui.components.LeadSourceComboBox;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ContactAddViewImpl extends AbstractView implements ContactAddView {
	private static final long serialVersionUID = 1L;
	

	@Override
	public void addNewItem() {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Contact>(new Contact()));
		this.addComponent(formItem);
	}

	@Override
	public void editItem(Contact item) {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Contact>(item));
		this.addComponent(formItem);
	}

	
	public void viewItem(Contact item) {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(ViewForm.class);
		formItem.setItemDataSource(new BeanItem<Contact>(item));
		this.addComponent(formItem);
	}


	public static class EditForm extends GenericForm {
		private static final long serialVersionUID = 1L;

		@Override
		protected HorizontalLayout createButtonControls() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			layout.setStyleName("addNewControl");
			FormActionListener formActionListener = new FormActionListener();
			Button saveBtn = new Button(SAVE_ACTION, formActionListener);
			Button cancelBtn = new Button(CANCEL_ACTION, formActionListener);

			layout.addComponent(saveBtn);
			layout.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);

			layout.addComponent(cancelBtn);
			layout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);
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
				Contact contact = ((BeanItem<Contact>) EditForm.this
						.getItemDataSource()).getBean();
				if (caption.equals(SAVE_ACTION)) {
					if (validateForm(contact)) {
						
					}
				} else if (caption.equals(CANCEL_ACTION)) {
					
				}
			}
		}
	}


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
					
				} else if (caption.equals(CANCEL_ACTION)) {
					
				}
			}
		}

	}

	public static abstract class GenericForm extends AdvancedEditBeanForm<Contact> {
		private static final long serialVersionUID = 1L;

		protected GridFormLayoutHelper informationLayout;

		protected GridFormLayoutHelper addressLayout;

		protected GridFormLayoutHelper descriptionLayout;

		public GenericForm() {

			AddViewLayout contactAddLayout = new AddViewLayout("Contact");

			contactAddLayout.addTopControls(createButtonControls());

			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);

			Label organizationHeader = new Label("Contact Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 9);
			informationLayout.getLayout().setWidth("900px");
			layout.addComponent(informationLayout.getLayout());

			Label addressHeader = new Label("Address Information");
			addressHeader.setStyleName("h2");
			layout.addComponent(addressHeader);
			addressLayout = new GridFormLayoutHelper(2, 5);
			addressLayout.getLayout().setWidth("900px");
			layout.addComponent(addressLayout.getLayout());

			descriptionLayout = new GridFormLayoutHelper(1, 1);
			Label descHeader = new Label("Description");
			descHeader.setStyleName("h2");
			layout.addComponent(descHeader);
			descriptionLayout.getLayout().setWidth("900px");
			descriptionLayout.getLayout().setColumnExpandRatio(1, 1.0f);
			layout.addComponent(descriptionLayout.getLayout());

			this.setWriteThrough(true);
			this.setInvalidCommitted(false);

			contactAddLayout.addBody(layout);
			contactAddLayout.addBottomControls(createButtonControls());

			setLayout(contactAddLayout);
		}

		abstract protected HorizontalLayout createButtonControls();

		/*
		 * Override to get control over where fields are placed.
		 */
		@Override
		protected void attachField(Object propertyId, Field field) {
			if (propertyId.equals("firstname")) {
				informationLayout.addComponent(field, "First Name", 0, 0);
			} else if (propertyId.equals("lastname")) {
				informationLayout.addComponent(field, "Last Name", 0, 1);
			} else if (propertyId.equals("accountid")) {
				informationLayout.addComponent(field, "Account", 0, 2);
			} else if (propertyId.equals("title")) {
				informationLayout.addComponent(field, "Title", 0, 3);
			} else if (propertyId.equals("department")) {
				informationLayout.addComponent(field, "Department", 0, 4);
			} else if (propertyId.equals("email")) {
				informationLayout.addComponent(field, "Email", 0, 5);
			} else if (propertyId.equals("assistant")) {
				informationLayout.addComponent(field, "Assistant", 0, 6);
			} else if (propertyId.equals("assistantphone")) {
				informationLayout.addComponent(field, "Assistant Phone", 0, 7);
			} else if (propertyId.equals("leadsource")) {
				informationLayout.addComponent(field, "Leade Source", 0, 8);
			} else if (propertyId.equals("officephone")) {
				informationLayout.addComponent(field, "Phone Office", 1, 0);
			} else if (propertyId.equals("mobile")) {
				informationLayout.addComponent(field, "Mobile", 1, 1);
			} else if (propertyId.equals("homephone")) {
				informationLayout.addComponent(field, "Home Phone", 1, 2);
			} else if (propertyId.equals("otherphone")) {
				informationLayout.addComponent(field, "Other Phone", 1, 3);
			} else if (propertyId.equals("fax")) {
				informationLayout.addComponent(field, "Fax", 1, 4);
			} else if (propertyId.equals("birthday")) {
				informationLayout.addComponent(field, "Birthday", 1, 5);
			} else if (propertyId.equals("iscallable")) {
				informationLayout.addComponent(field, "Callable", 1, 6);
			} else if (propertyId.equals("assignuser")) {
				informationLayout.addComponent(field, "Assign User", 1, 7);
			} else if (propertyId.equals("campaignid")) {
				informationLayout.addComponent(field, "Campaign", 1, 8);
			} else if (propertyId.equals("primaddress")) {
				addressLayout.addComponent(field, "Address", 0, 0);
			} else if (propertyId.equals("primcity")) {
				addressLayout.addComponent(field, "City", 0, 1);
			} else if (propertyId.equals("primstate")) {
				addressLayout.addComponent(field, "State", 0, 2);
			} else if (propertyId.equals("primpostalcode")) {
				addressLayout.addComponent(field, "Postal Code", 0, 3);
			} else if (propertyId.equals("primcountry")) {
				addressLayout.addComponent(field, "Country", 0, 4);
			} else if (propertyId.equals("otheraddress")) {
				addressLayout.addComponent(field, "Other Address", 1, 0);
			} else if (propertyId.equals("othercity")) {
				addressLayout.addComponent(field, "Other City", 1, 1);
			} else if (propertyId.equals("otherstate")) {
				addressLayout.addComponent(field, "Other State", 1, 2);
			} else if (propertyId.equals("otherpostalcode")) {
				addressLayout.addComponent(field, "Other Postal Code", 1, 3);
			} else if (propertyId.equals("othercountry")) {
				addressLayout.addComponent(field, "Other Country", 1, 4);
			} else if (propertyId.equals("description")) {
				field.setSizeUndefined();
				descriptionLayout.addComponent(
						propertyId.equals("description"), field, "Description",
						0, 0);
			}
		}
	}

}
