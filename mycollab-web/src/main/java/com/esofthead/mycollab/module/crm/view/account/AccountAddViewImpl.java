package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.ui.components.IndustryComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.CheckboxField;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

@ViewComponent
public class AccountAddViewImpl extends AbstractView implements AccountAddView {

	private class EditForm extends AdvancedEditBeanForm<Account> {

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {

				if ("type".equals(propertyId)) {
					AccountTypeComboBox accountTypeBox = new AccountTypeComboBox();
					return accountTypeBox;
				} else if ("industry".equals(propertyId)) {
					IndustryComboBox accountIndustryBox = new IndustryComboBox();
					return accountIndustryBox;
				} else if ("assignuser".equals(propertyId)) {
					UserComboBox userBox = new UserComboBox();
					userBox.select(account.getAssignuser());
					return userBox;
				} else if ("description".equals(propertyId)) {
					TextArea textArea = new TextArea("", "");
					textArea.setNullRepresentation("");
					return textArea;
				} else if ("billingcountry".equals(propertyId)
						|| "shippingcountry".equals(propertyId)) {
					CountryComboBox billingCountryComboBox = new CountryComboBox();
					return billingCountryComboBox;
				} else if ("id".equals(propertyId)) {
					final CheckboxField chkField = new CheckboxField();
					chkField.getCheckBox().addListener(
							new Button.ClickListener() {

								@Override
								public void buttonClick(ClickEvent event) {
									if ((Boolean) chkField.getCheckBox()
											.getValue()) {
										account.setShippingaddress(account
												.getBillingaddress());
										account.setShippingcity(account
												.getCity());
										account.setShippingstate(account
												.getState());
										account.setShippingpostalcode(account
												.getPostalcode());
										account.setShippingcountry(account
												.getBillingcountry());
									} else {
										account.setShippingaddress("");
										account.setShippingcity("");
										account.setShippingstate("");
										account.setShippingpostalcode("");
										account.setShippingcountry("");
									}

									isCheckToCopy = (Boolean) chkField
											.getCheckBox().getValue();
									editForm.setItemDataSource(new BeanItem<Account>(
											account));
								}

							});

					// if (account.getId() != null &&
					// account.getBillingaddress().equals(account.getShippingaddress())
					// && account.getCity().equals(account.getShippingcity())
					// && account.getState().equals(account.getShippingstate())
					// &&
					// account.getPostalcode().equals(account.getShippingpostalcode())
					// &&
					// account.getBillingcountry().equals(account.getShippingcountry()))
					// {
					// isCheckToCopy = true;
					// } else {
					// isCheckToCopy = false;
					// }

					chkField.getCheckBox().setValue(isCheckToCopy);

					return chkField;
				}

				if (propertyId.equals("accountname")) {
					TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(false);
					tf.setRequiredError("Please enter an Account Name");
					return tf;
				}

				return null;
			}
		}
		class FormLayoutFactory extends AccountFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super((account.getId() == null) ? "Create Account" : account
						.getAccountname());
			}

			@Override
			protected Layout createBottomPanel() {
				return createButtonControls();
			}

			private Layout createButtonControls() {
				return (new EditFormControlsGenerator<Account>(EditForm.this))
						.createButtonControls();
			}

			@Override
			protected Layout createTopPanel() {
				return createButtonControls();
			}
		}

		private static final long serialVersionUID = 1L;

		private boolean isCheckToCopy;

		@Override
		public void setItemDataSource(Item newDataSource) {
			setFormLayoutFactory(new FormLayoutFactory());
			setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}
	}
	private static final long serialVersionUID = 1L;
	private final EditForm editForm;

	private Account account;

	public AccountAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(Account account) {
		this.account = account;
		if (editForm.isCheckToCopy) {
			editForm.isCheckToCopy = false;
		}
		editForm.setItemDataSource(new BeanItem<Account>(account));
	}

	@Override
	public HasEditFormHandlers<Account> getEditFormHandlers() {
		return editForm;
	}
}
