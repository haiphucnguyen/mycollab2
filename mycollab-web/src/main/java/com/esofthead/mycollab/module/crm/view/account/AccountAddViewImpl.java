package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.ui.components.AccountTypeComboBox;
import com.esofthead.mycollab.module.crm.ui.components.IndustryComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormEditFieldFactory;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class AccountAddViewImpl extends AbstractView implements AccountAddView {
	private static final long serialVersionUID = 1L;

	private EditForm editFormItem;

	public AccountAddViewImpl() {
		editFormItem = new EditForm();
		this.addComponent(editFormItem);
	}

	@Override
	public void addNewItem() {
		editFormItem.setItemDataSource(new BeanItem<Account>(new Account()));
	}

	@Override
	public void editItem(Account account) {
		this.removeAllComponents();
		editFormItem.setItemDataSource(new BeanItem<Account>(account));
		this.addComponent(editFormItem);
	}

	public static class EditForm extends AdvancedEditBeanForm<Account> {
		private static final long serialVersionUID = 1L;

		public EditForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
		}

		class FormLayoutFactory extends AccountFormLayoutFactory {
			public FormLayoutFactory() {
				super(null);
			}

			@Override
			protected HorizontalLayout createButtonControls() {
				HorizontalLayout layout = new HorizontalLayout();
				layout.setSpacing(true);
				layout.setStyleName("addNewControl");
				Button saveBtn = new Button(SAVE_ACTION,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								@SuppressWarnings("unchecked")
								Account account = ((BeanItem<Account>) EditForm.this
										.getItemDataSource()).getBean();
								if (EditForm.this.validateForm(account)) {
									EditForm.this.fireSaveForm(account);
								}
							}
						});

				Button cancelBtn = new Button(CANCEL_ACTION, new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EditForm.this.fireCancelForm();
					}
				});

				layout.addComponent(saveBtn);
				layout.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);

				layout.addComponent(cancelBtn);
				layout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);
				return layout;
			}
		}

		private class EditFormFieldFactory extends DefaultFormEditFieldFactory {
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
	public HasEditFormHandlers<Account> getFormHandlers() {
		return editFormItem;
	}

}
