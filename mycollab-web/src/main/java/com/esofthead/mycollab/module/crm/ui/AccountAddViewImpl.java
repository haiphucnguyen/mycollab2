package com.esofthead.mycollab.module.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.ui.components.AccountTypeComboBox;
import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.module.crm.ui.components.IndustryComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@Component
public class AccountAddViewImpl extends AbstractView implements AccountAddView {
	private static final long serialVersionUID = 1L;

	@Override
	protected void initializeLayout() {
	}

	@Override
	public void addNewItem() {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Account>(new Account()));
		this.addComponent(formItem);
	}

	@Override
	public void editItem(Account account) {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Account>(account));
		this.addComponent(formItem);
	}

	@Override
	public void viewItem(Account item) {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(ViewForm.class);
		formItem.setItemDataSource(new BeanItem<Account>(item));
		this.addComponent(formItem);
	}

	@Scope("prototype")
	@Component
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

		private class EditFormFieldFactory extends DefaultFormEditFieldFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {

				if ("type".equals(propertyId)) {
					AccountTypeComboBox accountTypeBox = AppContext
							.getSpringBean(AccountTypeComboBox.class);
					return accountTypeBox;
				} else if ("industry".equals(propertyId)) {
					IndustryComboBox accountIndustryBox = AppContext
							.getSpringBean(IndustryComboBox.class);
					return accountIndustryBox;
				} else if ("assignuser".equals(propertyId)) {
					UserComboBox userBox = AppContext
							.getSpringBean(UserComboBox.class);
					return userBox;
				} else if ("description".equals(propertyId)) {
					TextArea textArea = new TextArea("", "");
					return textArea;
				}

				if (propertyId.equals("accountname")) {
					TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter an Account Name");
					return tf;
				}

				return null;
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
				Account account = ((BeanItem<Account>) EditForm.this
						.getItemDataSource()).getBean();
				if (caption.equals(SAVE_ACTION)) {
					if (validateForm(account)) {
						eventBus.fireEvent(new AccountEvent.Save(this, account));
					}
				} else if (caption.equals(CANCEL_ACTION)) {
					eventBus.fireEvent(new AccountEvent.GotoList(this, account));
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
			layout.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);

			layout.addComponent(cancelBtn);
			layout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);
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
				Account account = ((BeanItem<Account>) ViewForm.this
						.getItemDataSource()).getBean();
				if (caption.equals(EDIT_ACTION)) {
					eventBus.fireEvent(new AccountEvent.GotoEdit(this, account));
				} else if (caption.equals(CANCEL_ACTION)) {
					eventBus.fireEvent(new AccountEvent.GotoList(this, account));
				}
			}
		}

	}

	public static abstract class GenericForm extends AdvancedBeanForm<Account> {
		private static final long serialVersionUID = 1L;

		protected GridFormLayoutHelper informationLayout;

		protected GridFormLayoutHelper addressLayout;

		protected GridFormLayoutHelper descriptionLayout;

		public GenericForm() {
			super();

			AddViewLayout accountAddLayout = new AddViewLayout("Account");

			accountAddLayout.addTopControls(createButtonControls());

			VerticalLayout layout = new VerticalLayout();

			Label organizationHeader = new Label("Account Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 6);
			informationLayout.getLayout().setWidth("900px");
			layout.addComponent(informationLayout.getLayout());
			layout.setComponentAlignment(informationLayout.getLayout(),
					Alignment.BOTTOM_CENTER);

			addressLayout = new GridFormLayoutHelper(2, 4);
			Label addressHeader = new Label("Address Information");
			addressHeader.setStyleName("h2");
			layout.addComponent(addressHeader);
			addressLayout.getLayout().setWidth("900px");
			layout.addComponent(addressLayout.getLayout());
			layout.setComponentAlignment(addressLayout.getLayout(),
					Alignment.BOTTOM_CENTER);

			descriptionLayout = new GridFormLayoutHelper(1, 1);
			Label descHeader = new Label("Description");
			descHeader.setStyleName("h2");
			layout.addComponent(descHeader);
			descriptionLayout.getLayout().setWidth("900px");
			descriptionLayout.getLayout().setColumnExpandRatio(1, 1.0f);
			layout.addComponent(descriptionLayout.getLayout());

			this.setWriteThrough(true);
			this.setInvalidCommitted(false);

			accountAddLayout.addBottomControls(createButtonControls());

			accountAddLayout.addBody(layout);

			setLayout(accountAddLayout);
		}

		abstract protected HorizontalLayout createButtonControls();

		/*
		 * Override to get control over where fields are placed.
		 */
		@Override
		protected void attachField(Object propertyId, Field field) {
			informationLayout.addComponent(propertyId.equals("accountname"),
					field, "Account Name", 0, 0);
			informationLayout.addComponent(propertyId.equals("phoneoffice"),
					field, "Phone Office", 1, 0);
			informationLayout.addComponent(propertyId.equals("website"), field,
					"Website", 0, 1);
			informationLayout.addComponent(propertyId.equals("fax"), field,
					"Fax", 1, 1);
			informationLayout.addComponent(propertyId.equals("numemployees"),
					field, "Employees", 0, 2);
			informationLayout.addComponent(propertyId.equals("alternatephone"),
					field, "Other Phone", 1, 2);
			informationLayout.addComponent(propertyId.equals("industry"),
					field, "Industry", 0, 3);
			informationLayout.addComponent(propertyId.equals("email"), field,
					"Email", 1, 3);
			informationLayout.addComponent(propertyId.equals("type"), field,
					"Type", 0, 4);
			informationLayout.addComponent(propertyId.equals("ownership"),
					field, "Ownership", 1, 4);
			informationLayout.addComponent(propertyId.equals("assignuser"),
					field, "Assign User", 0, 5);
			informationLayout.addComponent(propertyId.equals("annualrevenue"),
					field, "Annual Revenue", 1, 5);

			addressLayout.addComponent(propertyId.equals("billingaddress"),
					field, "Billing Address", 0, 0);
			addressLayout.addComponent(propertyId.equals("shippingaddress"),
					field, "Shipping Address", 1, 0);
			addressLayout.addComponent(propertyId.equals("city"), field,
					"Billing City", 0, 1);
			addressLayout.addComponent(propertyId.equals("shippingcity"),
					field, "Shipping City", 1, 1);
			addressLayout.addComponent(propertyId.equals("state"), field,
					"Billing State", 0, 2);
			addressLayout.addComponent(propertyId.equals("shippingstate"),
					field, "Shipping State", 1, 2);
			addressLayout.addComponent(propertyId.equals("postalcode"), field,
					"Postal Code", 0, 3);
			addressLayout.addComponent(propertyId.equals("shippingpostalcode"),
					field, "Shipping Postal Code", 1, 3);

			if (propertyId.equals("description")) {
				field.setSizeUndefined();
				descriptionLayout.addComponent(
						propertyId.equals("description"), field, "Description",
						0, 0);
			}

		}
	}

}
