package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.ui.components.AccountTypeComboBox;
import com.esofthead.mycollab.module.crm.ui.components.IndustryComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.FormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class AccountAddViewImpl extends FormAddView<Account> implements AccountAddView {
	private static final long serialVersionUID = 1L;

	private EditForm editForm;

	public AccountAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	protected void onNewItem() {
		editForm.setItemDataSource(new BeanItem<Account>(new Account()));
	}

	@Override
	protected void onEditItem(Account account) {
		editForm.setItemDataSource(new BeanItem<Account>(account));
	}

	private static class EditForm extends AdvancedEditBeanForm<Account> {
		private static final long serialVersionUID = 1L;

		public EditForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
		}

		class FormLayoutFactory extends AccountFormLayoutFactory {

			@Override
			protected Layout createButtonControls() {
				return (new EditFormControlsGenerator<Account>(EditForm.this))
						.createButtonControls();
			}
		}

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
	}

	@Override
	public HasEditFormHandlers<Account> getEditFormHandlers() {
		return editForm;
	}

}
