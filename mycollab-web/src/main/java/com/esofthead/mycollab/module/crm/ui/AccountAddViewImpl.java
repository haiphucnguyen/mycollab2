package com.esofthead.mycollab.module.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.ui.components.IndustryComboBox;
import com.esofthead.mycollab.module.crm.ui.components.AccountTypeComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@Component
public class AccountAddViewImpl extends AbstractView implements AccountAddView {
	private static final long serialVersionUID = 1L;

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		return layout;
	}

	@Override
	public void addNewItem() {
		compContainer.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Account>(new Account()));
		compContainer.addComponent(formItem);
	}

	@Override
	public void editItem(Account account) {
		compContainer.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Account>(account));
		compContainer.addComponent(formItem);
	}

	@Override
	public void viewItem(Account item) {
		compContainer.removeAllComponents();
		Form formItem = AppContext.getSpringBean(ViewForm.class);
		formItem.setItemDataSource(new BeanItem<Account>(item));
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

				if ("type".equals(propertyId)) {
					AccountTypeComboBox accountTypeBox = AppContext
							.getSpringBean(AccountTypeComboBox.class);
					accountTypeBox.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
					return accountTypeBox;
				} else if ("industry".equals(propertyId)) {
					IndustryComboBox accountIndustryBox = AppContext
							.getSpringBean(IndustryComboBox.class);
					accountIndustryBox
							.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
					return accountIndustryBox;
				} else if ("assignuser".equals(propertyId)) {
					UserComboBox userBox = AppContext
							.getSpringBean(UserComboBox.class);
					userBox.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
					return userBox;
				} else if ("description".equals(propertyId)) {
					TextArea textArea = new TextArea("", "");
					textArea.setWidth(UIConstants.DEFAULT_2XCONTROL_WIDTH);
					textArea.setHeight(UIConstants.DEFAULT_2XCONTROL_HEIGHT);
					return textArea;
				}

				Field field = super.createField(item, propertyId, uiContext);

				if (field instanceof TextField) {
					((TextField) field).setNullRepresentation("");
					((TextField) field)
							.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
					((TextField) field).setCaption(null);
				}

				if (propertyId.equals("accountname")) {
					TextField tf = (TextField) field;
					tf.setRequired(true);
					tf.setRequiredError("Please enter an Account Name");
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

			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);

			layout.addComponent(createButtonControls());

			Label organizationHeader = new Label("Account Information");
			organizationHeader.setStyleName(Reindeer.LABEL_H2);
			layout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 6);
			layout.addComponent(informationLayout.getLayout());

			addressLayout = new GridFormLayoutHelper(2, 4);
			Label addressHeader = new Label("Address Information");
			addressHeader.setStyleName(Reindeer.LABEL_H2);
			layout.addComponent(addressHeader);
			layout.addComponent(addressLayout.getLayout());

			descriptionLayout = new GridFormLayoutHelper(2, 1);
			Label descHeader = new Label("Description");
			descHeader.setStyleName(Reindeer.LABEL_H2);
			layout.addComponent(descHeader);
			layout.addComponent(descriptionLayout.getLayout());

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

			descriptionLayout.addComponent(propertyId.equals("description"),
					field, "Description", 0, 0, 1, 0);
		}
	}

}
