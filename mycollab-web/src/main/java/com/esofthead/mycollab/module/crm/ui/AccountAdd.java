package com.esofthead.mycollab.module.crm.ui;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.ui.components.AccountTypeComboBox;
import com.esofthead.mycollab.vaadin.ui.Hr;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AccountAdd extends VerticalLayout implements IPage,
		Button.ClickListener {
	private static final long serialVersionUID = 1L;

	private static String SAVE_ACTION = "Save";

	private static String CANCEL_ACTION = "Cancel";

	private Account account;

	public AccountAdd() {
		account = new Account();

		this.setSpacing(true);

		this.addComponent(new Label("Create Account"));
		this.addComponent(new Hr());

		this.addComponent(createButtonControls());
		BeanItem<Account> item = new BeanItem<Account>(account);

		Form form = new AccountForm(item);
		this.addComponent(form);

		this.addComponent(createButtonControls());
	}

	private HorizontalLayout createButtonControls() {
		HorizontalLayout layout = new HorizontalLayout();
		Button saveBtn = new Button(SAVE_ACTION, this);
		Button cancelBtn = new Button(CANCEL_ACTION, this);

		layout.addComponent(saveBtn);
		layout.addComponent(cancelBtn);
		return layout;
	}

	private class AccountForm extends Form {
		private static final long serialVersionUID = 1L;

		private GridLayout customLayout;

		public AccountForm(BeanItem<Account> item) {
			customLayout = new GridLayout(3, 3);
			customLayout.setMargin(true, false, false, true);
			customLayout.setSpacing(true);

			setLayout(customLayout);

			customLayout.addComponent(new Label("Account Information"), 0, 0);

			setFormFieldFactory(new AccountFormFieldFactory());
			setItemDataSource(item);
		}

		/*
		 * Override to get control over where fields are placed.
		 */
		@Override
		protected void attachField(Object propertyId, Field field) {
			if (propertyId.equals("accountname")) {
				customLayout.addComponent(field, 0, 1);
			} else if (propertyId.equals("phoneoffice")) {
				customLayout.addComponent(field, 1, 1);
			} else if (propertyId.equals("type")) {
				customLayout.addComponent(field, 0, 2);
			}
		}
	}

	private class AccountFormFieldFactory extends DefaultFieldFactory {
		private static final long serialVersionUID = 1L;

		public AccountFormFieldFactory() {

		}

		@Override
		public Field createField(Item item, Object propertyId,
				Component uiContext) {

			if ("type".equals(propertyId)) {
				AccountTypeComboBox accountTypeBox = new AccountTypeComboBox();
				return accountTypeBox;
			}

			Field field = super.createField(item, propertyId, uiContext);

			if (field instanceof TextField) {
				((TextField) field).setNullRepresentation("");
			}

			if (propertyId.equals("accountname")) {
				TextField tf = (TextField) field;
				tf.setCaption("Account Name");
				tf.setRequired(true);
				tf.setRequiredError("Please enter an Account Name");
			} else if (propertyId.equals("phoneoffice")) {
				TextField tf = (TextField) field;
				tf.setCaption("Phone Office");
			} else {
				return null;
			}

			return field;
		}
	}

	// ==================================================
	// Actions
	// ==================================================
	@Override
	public void buttonClick(ClickEvent event) {
		String caption = event.getButton().getCaption();

		if (caption.equals(SAVE_ACTION)) {
			
		} else if (caption.equals(CANCEL_ACTION)) {

		}

	}
}
